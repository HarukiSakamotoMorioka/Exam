package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    private String baseSql = "select * from student where school_cd = ? ";

    // 1件取得
    public Student get(String no) throws Exception {
        Student student = null;
        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement("select * from student where no=?");
            st.setString(1, no);
            ResultSet rs = st.executeQuery();

            SchoolDao schoolDao = new SchoolDao();

            if (rs.next()) {
                student = new Student();
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setAttend(rs.getBoolean("is_attend"));
                student.setSchool(schoolDao.get(rs.getString("school_cd")));
            }
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return student;
    }

    // リスト変換
    private List<Student> postFilter(ResultSet rs, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("no"));
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            s.setAttend(rs.getBoolean("is_attend"));
            s.setSchool(school);
            list.add(s);
        }
        return list;
    }

    // フィルタ（3条件）
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;

        String condition = " and ent_year=? and class_num=? ";
        String attend = isAttend ? " and is_attend=true " : "";
        String order = " order by no asc ";

        try {
            st = con.prepareStatement(baseSql + condition + attend + order);
            st.setString(1, school.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);

            ResultSet rs = st.executeQuery();
            list = postFilter(rs, school);

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return list;
    }

    // フィルタ（2条件）
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;

        String condition = " and ent_year=? ";
        String attend = isAttend ? " and is_attend=true " : "";
        String order = " order by no asc ";

        try {
            st = con.prepareStatement(baseSql + condition + attend + order);
            st.setString(1, school.getCd());
            st.setInt(2, entYear);

            ResultSet rs = st.executeQuery();
            list = postFilter(rs, school);

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return list;
    }

    // フィルタ（1条件）
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;

        String attend = isAttend ? " and is_attend=true " : "";
        String order = " order by no asc ";

        try {
            st = con.prepareStatement(baseSql + attend + order);
            st.setString(1, school.getCd());

            ResultSet rs = st.executeQuery();
            list = postFilter(rs, school);

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return list;
    }

    // 🔥 新規登録（INSERT）
    public boolean insert(Student s) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            st = con.prepareStatement(
                "INSERT INTO student(no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)"
            );

            st.setString(1, s.getNo());
            st.setString(2, s.getName());
            st.setInt(3, s.getEntYear());
            st.setString(4, s.getClassNum());
            st.setBoolean(5, s.isAttend());
            st.setString(6, s.getSchool().getCd());

            count = st.executeUpdate();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return count > 0;
    }

    // 🔄 更新（UPDATE）
    public boolean update(Student s) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            st = con.prepareStatement(
                "UPDATE student SET name=?, ent_year=?, class_num=?, is_attend=?, school_cd=? WHERE no=?"
            );

            st.setString(1, s.getName());
            st.setInt(2, s.getEntYear());
            st.setString(3, s.getClassNum());
            st.setBoolean(4, s.isAttend());
            st.setString(5, s.getSchool().getCd());
            st.setString(6, s.getNo());

            count = st.executeUpdate();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return count > 0;
    }

    // ❗ save は insert/update を自動判定
    public boolean save(Student s) throws Exception {
        if (get(s.getNo()) == null) {
            return insert(s);
        } else {
            return update(s);
        }
    }

    // 削除
    public boolean delete(String no, String schoolCd) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            st = con.prepareStatement("DELETE FROM student WHERE no=? AND school_cd=?");
            st.setString(1, no);
            st.setString(2, schoolCd);
            count = st.executeUpdate();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return count > 0;
    }

    // 入学年度一覧
    public List<Integer> getEntYearList(String schoolCd) throws Exception {
        List<Integer> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT ent_year FROM student WHERE school_cd = ? ORDER BY ent_year DESC"
        );
        st.setString(1, schoolCd);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getInt("ent_year"));
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}
