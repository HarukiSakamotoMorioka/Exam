package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

	//全科目取得
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT school, cd, name FROM subject ORDER BY cd";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subject s = new Subject();
                s.setSchool(rs.getString("school"));
                s.setCd(rs.getString("cd"));
                s.setName(rs.getString("name"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ← ここに赤いエラーが出てるはず
        }

        return list;
    }
    
    
    
    
    
    //検索科目取得
    public List<Subject> filter(String school) {
        List<Subject> list = new ArrayList<>();

        try {
            Connection con = getConnection();

            String sql = "SELECT school, cd, name FROM subject WHERE school = ? ORDER BY cd";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, school);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setSchool(rs.getString("school"));
                s.setCd(rs.getString("cd"));
                s.setName(rs.getString("name"));
                list.add(s);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}


	

	