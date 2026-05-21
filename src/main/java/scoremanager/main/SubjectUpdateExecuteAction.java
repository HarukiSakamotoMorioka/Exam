package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ パラメータ取得
        String code = request.getParameter("subjectCode");
        String name = request.getParameter("subjectName");

        // ▼ ログイン中の先生を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        SubjectDao dao = new SubjectDao();

        // ▼ バリデーション（科目名）
        if (name == null || name.isEmpty()) {

            request.setAttribute("errorMsg", "科目名を入力してください");

            // 再表示用に科目情報を取得
            Subject subject = dao.find(schoolCd, code);
            request.setAttribute("subject", subject);

            request.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
                   .forward(request, response);
            return;
        }

        // ▼ 更新処理
        Subject subject = new Subject();
        subject.setCd(code);
        subject.setName(name);
        subject.setSchool(schoolCd);

        dao.update(subject);

        // ▼ 完了画面へ
        response.sendRedirect("subject_update_done.jsp");
    }
}
