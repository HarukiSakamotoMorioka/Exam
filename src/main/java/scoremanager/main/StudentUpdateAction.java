package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher teacher =
            (Teacher)session.getAttribute("user");

        // 学生番号取得
        String no = req.getParameter("no");

        // DAO
        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // 学生情報取得
        Student student =
            studentDao.get(no);

        // クラス一覧取得
        List<String> classList =
            classNumDao.filter(
                teacher.getSchool().getCd()
            );

        // JSPへ値を渡す
        req.setAttribute("student", student);
        req.setAttribute("classList", classList);

        // JSPへフォワード
        req.getRequestDispatcher(
            "/scoremanager/main/student_update.jsp"
        ).forward(req, res);

    }

}