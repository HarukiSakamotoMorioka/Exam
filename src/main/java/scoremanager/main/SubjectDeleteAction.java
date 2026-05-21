package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ パラメータ取得
        String cd = request.getParameter("cd");

        // ▼ ログイン中の先生の school_cd を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        // ▼ 科目取得（学校コード + 科目コード）
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.find(schoolCd, cd);

        // ▼ JSP に渡す
        request.setAttribute("subject", subject);

        // ▼ 削除確認画面へ
        request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp")
               .forward(request, response);
    }
}
