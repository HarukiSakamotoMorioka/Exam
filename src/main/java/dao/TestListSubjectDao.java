package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private static final String SQL =
        "SELECT " +
        "    st.ent_year, " +
        "    st.class_num, " +
        "    st.no AS student_no, " +
        "    st.name AS student_name, " +
        "    t.no AS test_no, " +
        "    t.point AS point, " +
        "    sub.name AS subject_name " +
        "FROM test t " +
        "JOIN student st ON t.student_no = st.no AND t.school_cd = st.school_cd " +
        "JOIN subject sub ON t.subject_cd = sub.cd AND t.school_cd = sub.school_cd " +
        "WHERE t.school_cd = ? " +
        "  AND t.subject_cd = ? " +
        "  AND st.ent_year = ? " +
        "  AND st.class_num = ? " +
        "ORDER BY st.no, t.no";

    private List<TestListSubject> postfilter(ResultSet rs) throws Exception {

        // ★ 学生番号ごとに1行にまとめるための Map
        Map<String, TestListSubject> map = new LinkedHashMap<>();

        while (rs.next()) {

            String studentNo = rs.getString("student_no");

            // すでに存在する学生なら取得、なければ新規作成
            TestListSubject t = map.get(studentNo);
            if (t == null) {
                t = new TestListSubject();
                t.setEntYear(rs.getInt("ent_year"));
                t.setClassNum(rs.getString("class_num"));
                t.setStudentNo(studentNo);
                t.setStudentName(rs.getString("student_name"));
                t.setSubjectName(rs.getString("subject_name"));
                map.put(studentNo, t);
            }

            // ★ 1回・2回の点数を振り分ける
            int testNo = rs.getInt("test_no");
            int point = rs.getInt("point");

            if (testNo == 1) {
                t.setPoint1(point);
            } else if (testNo == 2) {
                t.setPoint2(point);
            }
        }

        return new ArrayList<>(map.values());
    }

    public List<TestListSubject> filter(String schoolCd, String subjectCd,
                                        int entYear, String classNum) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement(SQL);
            st.setString(1, schoolCd);
            st.setString(2, subjectCd);
            st.setInt(3, entYear);
            st.setString(4, classNum);

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
