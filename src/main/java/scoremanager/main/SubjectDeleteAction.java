package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ 削除対象の科目コードを取得
        String cd = request.getParameter("cd");

        if (cd == null || cd.isEmpty()) {
            // 科目コードが無い場合は一覧へ戻す
            response.sendRedirect("SubjectList.action");
            return;
        }

        // ▼ DAO を使って削除（学校コード 001 を使用）
        SubjectDao dao = new SubjectDao();
        dao.delete(cd, "001");

        // ▼ 削除完了画面へフォワード
        request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp")
               .forward(request, response);
    }
}