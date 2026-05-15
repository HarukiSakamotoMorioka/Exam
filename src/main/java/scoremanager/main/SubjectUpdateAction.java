package scoremanager.main;

import bean.Subject;
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

        SubjectDao dao = new SubjectDao();

        // ▼ name が null → 画面を開いたとき（GET）
        if (name == null) {

            // DB から科目情報を取得
            Subject subject = dao.find(code);

            // JSP に渡す
            request.setAttribute("subject", subject);

            // 更新画面へ
            forward(request, response);
            return;
        }

        // ▼ name が空 → バリデーションエラー（POST）
        if (name.isEmpty()) {
            request.setAttribute("errorMsg", "科目名を入力してください");

            Subject subject = dao.find(code);
            request.setAttribute("subject", subject);

            forward(request, response);
            return;
        }

        // ▼ 更新処理（POST）
        Subject subject = new Subject();
        subject.setCd(code);
        subject.setName(name);
        subject.setSchool("001");

        dao.update(subject);

        // 一覧へ
        response.sendRedirect("SubjectList.action");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(request, response);
    }
}

