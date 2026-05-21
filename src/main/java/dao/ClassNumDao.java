package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassNumDao extends Dao {

	//一覧取得
    public List<String> filter(String schoolCd) throws Exception {

        List<String> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            String sql = "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num ASC";

            statement = connection.prepareStatement(sql);
            statement.setString(1, schoolCd);
            rSet = statement.executeQuery();

            while (rSet.next()) {
                list.add(rSet.getString("class_num"));
            }

        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }
    
    //新規登録
    public boolean insert(String schoolCd, String classNum) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement(
                "INSERT INTO class_num (school_cd, class_num) VALUES (?, ?)"
            );
            st.setString(1, schoolCd);
            st.setString(2, classNum);

            return st.executeUpdate() > 0;

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }

    
    //更新
    public boolean update(String schoolCd, String oldNum, String newNum) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement(
                "UPDATE class_num SET class_num = ? WHERE school_cd = ? AND class_num = ?"
            );
            st.setString(1, newNum);
            st.setString(2, schoolCd);
            st.setString(3, oldNum);

            return st.executeUpdate() > 0;

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }

    
    //削除
    public boolean delete(String schoolCd, String classNum) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement(
                "DELETE FROM class_num WHERE school_cd = ? AND class_num = ?"
            );
            st.setString(1, schoolCd);
            st.setString(2, classNum);

            return st.executeUpdate() > 0;

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }

}
