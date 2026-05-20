package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ パラメータ取得
        String cd = request.getParameter("cd");

        // ▼ 科目取得（cd のみ）
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.find(cd);

        // ▼ JSP に渡す
        request.setAttribute("subject", subject);

        // ▼ 削除確認画面へ
        request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp")
               .forward(request, response);
    }
}
