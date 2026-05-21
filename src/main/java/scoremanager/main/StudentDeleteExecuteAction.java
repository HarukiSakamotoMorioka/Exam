package scoremanager.main;

import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ パラメータ取得
        String no = request.getParameter("no");

        // ▼ school_cd取得（ログイン情報から）
        String schoolCd =
            ((bean.Teacher)request.getSession().getAttribute("user"))
            .getSchool().getCd();

        // ▼ 削除処理
        StudentDao dao = new StudentDao();
        dao.delete(no, schoolCd);

        // ▼ 完了画面へ
        request.getRequestDispatcher("/scoremanager/main/student_delete_done.jsp")
               .forward(request, response);
    }
}