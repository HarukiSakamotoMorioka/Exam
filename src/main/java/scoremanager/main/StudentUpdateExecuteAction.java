package scoremanager.main;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res)
            throws Exception {

        // セッション取得
        HttpSession session =
            req.getSession();

        Teacher teacher =
            (Teacher)session.getAttribute("user");

        School school =
            teacher.getSchool();

        // パラメータ取得
        String no =
            req.getParameter("no");

        String name =
            req.getParameter("name");

        String classNum =
            req.getParameter("class_num");

        int entYear =
            Integer.parseInt(
                req.getParameter("ent_year")
            );

        boolean isAttend =
            req.getParameter("is_attend") != null;

        // Student生成
        Student student =
            new Student();

        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(school);

        // 更新
        StudentDao dao =
            new StudentDao();

        dao.update(student);

        // 完了画面へ
        req.getRequestDispatcher(
            "student_update_done.jsp"
        ).forward(req, res);
    }
}