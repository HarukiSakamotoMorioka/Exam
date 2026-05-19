package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ パラメータ取得
        String code = request.getParameter("subjectCode");
        String name = request.getParameter("subjectName");

        // ▼ ログイン中の先生を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        SubjectDao dao = new SubjectDao();

        // ▼ name が null → 画面を開いたとき（GET）
        if (name == null) {

            // ★ 学校コード + 科目コードで取得
            Subject subject = dao.find(schoolCd, code);

            request.setAttribute("subject", subject);
            forward(request, response);
            return;
        }

        // ▼ name が空 → バリデーションエラー（POST）
        if (name.isEmpty()) {
            request.setAttribute("errorMsg", "科目名を入力してください");

            // ★ 再表示用に取得
            Subject subject = dao.find(schoolCd, code);
            request.setAttribute("subject", subject);

            forward(request, response);
            return;
        }

        // ▼ 更新処理（POST）
        Subject subject = new Subject();
        subject.setCd(code);
        subject.setName(name);
        subject.setSchool(schoolCd);  // ★ 先生の school_cd をセット

        dao.update(subject);

        response.sendRedirect("SubjectUpdateDone.action");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(request, response);
    }
}
