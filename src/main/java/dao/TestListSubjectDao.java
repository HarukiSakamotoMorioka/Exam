package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private static final String SQL =
        "SELECT t.student_no, t.subject_cd, sub.name AS subject_name, " +
        "       t.no, t.point, t.class_num " +
        "FROM test t " +
        "JOIN student st ON t.student_no = st.no AND t.school_cd = st.school_cd " +
        "JOIN subject sub ON t.subject_cd = sub.cd AND t.school_cd = sub.school " +
        "WHERE t.school_cd = ? " +
        "  AND t.subject_cd = ? " +
        "  AND st.ent_year = ? " +
        "  AND st.class_num = ? " +
        "ORDER BY t.student_no, t.no";

    public List<TestListSubject> filter(String school, String subjectCd, int entYear, String classNum) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(SQL);
        st.setString(1, school);
        st.setString(2, subjectCd);
        st.setInt(3, entYear);
        st.setString(4, classNum);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            TestListSubject t = new TestListSubject();
            t.setStudentNo(rs.getString("student_no"));
            t.setSubjectCd(rs.getString("subject_cd"));
            t.setSubjectName(rs.getString("subject_name"));
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
