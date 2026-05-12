package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    // ① 入学年度＋クラス検索
    private static final String SQL_FILTER =
        "SELECT t.student_no, t.subject_cd, t.no, t.point, t.class_num " +
        "FROM test t " +
        "JOIN student s ON t.student_no = s.no AND t.school_cd = s.school_cd " +
        "WHERE t.school_cd = ? " +
        "  AND s.ent_year = ? " +
        "  AND s.class_num = ? " +
        "ORDER BY t.student_no, t.no";

    // ② 学生番号検索
    private static final String SQL_FILTER_BY_STUDENT =
        "SELECT t.student_no, t.subject_cd, t.no, t.point, t.class_num " +
        "FROM test t " +
        "WHERE t.school_cd = ? " +
        "  AND t.student_no = ? " +
        "ORDER BY t.no";

    // ① 入学年度＋クラス検索
    public List<TestListStudent> filter(String school, int entYear, String classNum) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(SQL_FILTER);
        st.setString(1, school);
        st.setInt(2, entYear);
        st.setString(3, classNum);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            TestListStudent t = new TestListStudent();
            t.setStudentNo(rs.getString("student_no"));
            t.setSubjectCd(rs.getString("subject_cd"));
            t.setNo(rs.getInt("no"));
            t.setPoint(rs.getInt("point"));
            t.setClassNum(rs.getString("class_num"));
            list.add(t);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // ② 学生番号検索
    public List<TestListStudent> filterByStudentNo(String school, String studentNo) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(SQL_FILTER_BY_STUDENT);
        st.setString(1, school);
        st.setString(2, studentNo);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            TestListStudent t = new TestListStudent();
            t.setStudentNo(rs.getString("student_no"));
            t.setSubjectCd(rs.getString("subject_cd"));
            t.setNo(rs.getInt("no"));
            t.setPoint(rs.getInt("point"));
            t.setClassNum(rs.getString("class_num"));
            list.add(t);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}
