package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao {

    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // クラス番号を取得するSQL（例：class_num テーブルがある前提）
            String sql = "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num ASC";

            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
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
}
