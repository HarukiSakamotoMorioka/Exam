package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private static final String SQL =
        "SELECT no, name, ent_year, class_num, school_cd " +
        "FROM student WHERE school_cd = ? AND ent_year = ? AND class_num = ?";

    public List<TestListStudent> filter(String school, int entYear, String classNum) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(SQL);
        st.setString(1, school);
        st.setInt(2, entYear);
        st.setString(3, classNum);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            TestListStudent s = new TestListStudent();
            s.setStudentNo(rs.getString("no"));
            s.setStudentName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            // school_cd は DTO に入れない設計なのでセットしない
            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}
