package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateDoneAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 完了画面へフォワード
        request.getRequestDispatcher("/scoremanager/main/subject_create_done.jsp")
               .forward(request, response);
    }
}
