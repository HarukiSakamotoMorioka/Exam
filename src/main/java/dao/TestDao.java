package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    // ============================
    // ① 単体取得（学生 × 科目 × 回数）
    // ============================
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Test test = null;

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement(
                "SELECT * FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?"
            );

            st.setString(1, student.getNo());
            st.setString(2, subject.getCd());
            st.setString(3, school.getCd());
            st.setInt(4, no);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setClassNum(rs.getString("class_num"));
                test.setNo(no);
                test.setPoint(rs.getInt("point"));
            }

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return test;
    }

    // ============================
    // ② 一覧取得（入学年度 × クラス × 科目 × 回数）
    // ============================
    private List<Test> postFilter(ResultSet rs, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        while (rs.next()) {

            Test test = new Test();
            Student student = new Student();
            Subject subject = new Subject();

            // 学生情報
            student.setNo(rs.getString("student_no"));
            student.setName(rs.getString("student_name"));
            student.setEntYear(rs.getInt("ent_year"));
            student.setClassNum(rs.getString("class_num"));
            student.setSchool(school);

            // 科目情報
            subject.setCd(rs.getString("subject_cd"));
            subject.setName(rs.getString("subject_name"));

            // テスト情報
            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setClassNum(rs.getString("class_num"));
            test.setNo(rs.getInt("num"));
            test.setPoint(rs.getInt("point"));

            list.add(test);
        }

        return list;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql =
            "SELECT t.student_no, " +
            "       st.ent_year, " +
            "       st.name AS student_name, " +
            "       s.cd, t.subject_cd, " +
            "       s.name AS subject_name, " +
            "       t.no AS num, " +
            "       t.point, " +
            "       t.class_num, " +
            "       t.school_cd " +
            "FROM test t " +
            "JOIN subject s ON t.subject_cd = s.cd AND t.school_cd = s.school_cd " +
            "JOIN student st ON t.student_no = st.no " +
            "WHERE st.ent_year = ? " +
            "  AND t.class_num = ? " +
            "  AND t.subject_cd = ? " +
            "  AND t.no = ? " +
            "ORDER BY st.no";

        try {
            st = con.prepareStatement(sql);
            st.setInt(1, entYear);
            st.setString(2, classNum);
            st.setString(3, subject.getCd());
            st.setInt(4, num);

            rs = st.executeQuery();
            list = postFilter(rs, school);

            if (list.isEmpty()) {
                list = null;
            }

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return list;
    }

    // ============================
    // ③ 保存（INSERT or UPDATE）
    // ============================
    public boolean save(List<Test> list) throws Exception {

        Connection con = getConnection();

        for (Test test : list) {
            save(test, con);
        }

        con.close();
        return true;
    }

    private boolean save(Test test, Connection con) throws Exception {

        PreparedStatement st = null;
        int count = 0;

        try {
            st = con.prepareStatement(
                "MERGE INTO test KEY(student_no, subject_cd, school_cd, no) " +
                "VALUES(?,?,?,?,?,?)"
            );

            st.setString(1, test.getStudent().getNo());
            st.setString(2, test.getSubject().getCd());
            st.setString(3, test.getSchool().getCd());
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, test.getClassNum());

            count = st.executeUpdate();

        } finally {
            if (st != null) st.close();
        }

        return count > 0;
    }
}
