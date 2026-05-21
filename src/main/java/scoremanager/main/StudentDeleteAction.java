package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ▼ パラメータ取得（学生番号）
        String no = request.getParameter("no");

        // ▼ 学生取得
        StudentDao dao = new StudentDao();
        Student student = dao.get(no);

        // ▼ JSPへ渡す
        request.setAttribute("student", student);

        // ▼ 削除確認画面へ
        request.getRequestDispatcher("/scoremanager/main/student_delete.jsp")
               .forward(request, response);
    }
}