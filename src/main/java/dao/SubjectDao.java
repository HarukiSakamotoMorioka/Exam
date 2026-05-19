package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // 全科目取得（学校関係なし）
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();

        String sql = "SELECT school_cd, cd, name FROM subject ORDER BY cd";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subject s = new Subject();
                s.setSchool(rs.getString("school_cd"));
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

        String sql = "SELECT school_cd, cd, name FROM subject WHERE school_cd = ? ORDER BY cd";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, schoolCd);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subject s = new Subject();
                    s.setSchool(rs.getString("school_cd"));
                    s.setCd(rs.getString("cd"));
                    s.setName(rs.getString("name"));
                    list.add(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ★ 科目コード + 学校コードで1件取得（重複チェック用）
    public Subject find(String schoolCd, String cd) {
        Subject subject = null;

        String sql = "SELECT school_cd, cd, name FROM subject WHERE school_cd = ? AND cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, schoolCd);
            ps.setString(2, cd);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setSchool(rs.getString("school_cd"));
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

            ps.setString(1, subject.getSchool());
            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getName());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 科目更新（学校コードは変更しない）
    public void update(Subject subject) {
        String sql = "UPDATE subject SET name = ? WHERE school_cd = ? AND cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getName());
            ps.setString(2, subject.getSchool());
            ps.setString(3, subject.getCd());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 科目削除（★ school → school_cd に修正）
    public void delete(String cd, String schoolCd) throws Exception {

        String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cd);
            ps.setString(2, schoolCd);

            ps.executeUpdate();

        }
    }
}
