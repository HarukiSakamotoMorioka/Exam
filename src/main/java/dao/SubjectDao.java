package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // 全科目取得
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT school_cd, cd, name FROM subject ORDER BY cd";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subject s = new Subject();
                s.setSchool(rs.getString("school_cd"));  // ★ setSchool に統一
                s.setCd(rs.getString("cd"));
                s.setName(rs.getString("name"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 学校コードで科目を絞り込み
    public List<Subject> filter(String schoolCd) {
        List<Subject> list = new ArrayList<>();

        try {
            Connection con = getConnection();

            // ★ school → school_cd に修正
            String sql = "SELECT school_cd, cd, name FROM subject WHERE school_cd = ? ORDER BY cd";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, schoolCd);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setSchool(rs.getString("school_cd"));  // ★ setSchool
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

    // 科目コードで1件取得
    public Subject find(String cd) {
        Subject subject = null;

        // ★ school → school_cd に修正
        String sql = "SELECT school_cd, cd, name FROM subject WHERE cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cd);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setSchool(rs.getString("school_cd"));  // ★ setSchool
                    subject.setCd(rs.getString("cd"));
                    subject.setName(rs.getString("name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    // 科目登録
    public void insert(Subject subject) {
        String sql = "INSERT INTO subject (school_cd, cd, name) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getSchool());  // ★ getSchool に統一
            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getName());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
