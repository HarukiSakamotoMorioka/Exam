package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ 入力値取得
        String code = request.getParameter("subjectCode");
        String name = request.getParameter("subjectName");

        // ▼ ログイン中の先生を取得（session に入っている）
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();   // ← 先生の school_cd を取得

        // ▼ 未入力チェック
        if (code == null || code.isEmpty()) {
            request.setAttribute("errorCodeMsg", "科目コードを入力してください");
            forward(request, response);
            return;
        }

        if (name == null || name.isEmpty()) {
            request.setAttribute("errorMsg", "科目名を入力してください");
            forward(request, response);
            return;
        }

        // ▼ 文字数チェック（3文字固定）
        if (code.length() != 3) {
            request.setAttribute("errorLengthMsg", "科目コードは3文字で入力してください");
            forward(request, response);
            return;
        }

        // ▼ 重複チェック（学校コード + 科目コード）
        SubjectDao dao = new SubjectDao();
        Subject exist = dao.find(schoolCd, code);   // ← DAO 側に複合キー find が必要

        if (exist != null) {
            request.setAttribute("errorDuplicateMsg", "科目コードが重複しています");
            forward(request, response);
            return;
        }

        // ▼ 登録処理
        Subject subject = new Subject();
        subject.setCd(code);
        subject.setName(name);
        subject.setSchool(schoolCd);   // ← teacher の school_cd をセット

        dao.insert(subject);

        // ▼ 完了画面へ
        response.sendRedirect("SubjectCreateDone.action");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/scoremanager/main/subject_create.jsp")
               .forward(request, response);
    }
}
