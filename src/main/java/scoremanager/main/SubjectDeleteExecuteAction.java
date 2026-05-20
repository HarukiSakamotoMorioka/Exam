package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ パラメータ取得（科目コード）
        String cd = request.getParameter("cd");

        // ▼ 削除処理（cd のみ）
        SubjectDao dao = new SubjectDao();
        dao.delete(cd);

        // ▼ 完了画面へ
        request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp")
               .forward(request, response);
    }
}
