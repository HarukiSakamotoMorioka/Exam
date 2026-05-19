package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private static final String SQL =
        "SELECT " +
        "  t.student_no, " +
        "  st.name AS student_name, " +
        "  t.subject_cd, " +
        "  sb.name AS subject_name, " +
        "  t.no, " +
        "  t.point, " +
        "  t.class_num " +
        "FROM test t " +
        "JOIN student st ON t.student_no = st.no AND t.school_cd = st.school_cd " +
        "JOIN subject sb ON t.subject_cd = sb.cd AND t.school_cd = sb.school_cd " +
        "WHERE t.student_no = ? " +
        "  AND t.school_cd = ? " +
        "ORDER BY t.subject_cd, t.no";

    private List<TestListStudent> postfilter(ResultSet rs) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        while (rs.next()) {
            TestListStudent t = new TestListStudent();

            t.setStudentNo(rs.getString("student_no"));
            t.setStudentName(rs.getString("student_name"));
            t.setSubjectCd(rs.getString("subject_cd"));
            t.setSubjectName(rs.getString("subject_name"));
            t.setNo(rs.getInt("no"));
            t.setPoint(rs.getInt("point"));
            t.setClassNum(rs.getString("class_num"));

            list.add(t);
        }

        return list;
    }

    public List<TestListStudent> filterByStudentNo(String schoolCd, String studentNo) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement(SQL);
            st.setString(1, studentNo);
            st.setString(2, schoolCd);

            rs = st.executeQuery();
            list = postfilter(rs);

        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (st != null) try { st.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }

        return list;
    }
}
