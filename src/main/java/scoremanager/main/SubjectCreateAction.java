package scoremanager.main;

import bean.Subject;
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

        // ▼ 重複チェック
        SubjectDao dao = new SubjectDao();
        Subject exist = dao.find(code);   // ← DAO に find を作る必要あり

        if (exist != null) {
            request.setAttribute("errorDuplicateMsg", "科目コードが重複しています");
            forward(request, response);
            return;
        }

        // ▼ 登録処理
        Subject subject = new Subject();
        subject.setCd(code);     // ← setCode ではなく setCd
        subject.setName(name);
        subject.setSchool("001");   // ★学校コード（ログインユーザーの学校コードを入れる）

        dao.insert(subject);     // ← DAO に insert を作る必要あり

        response.sendRedirect("SubjectCreateDone.action");

    }

    private void forward(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/scoremanager/main/subject_create.jsp")
               .forward(request, response);
    }
}

