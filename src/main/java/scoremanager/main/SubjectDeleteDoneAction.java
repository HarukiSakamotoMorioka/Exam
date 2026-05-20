package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteDoneAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ただ削除完了画面へ遷移するだけ
        request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp")
               .forward(request, response);
    }
}
