package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession(false);

        // ここ修正
        Teacher teacher = (Teacher) session.getAttribute("user");

        School school = teacher.getSchool();

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        String entYearParam = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        boolean isAttend = "on".equals(req.getParameter("is_attend"));

        List<Student> studentList;

        if (entYearParam != null && !entYearParam.isEmpty()
                && classNum != null && !classNum.isEmpty()) {

            studentList = studentDao.filter(
                    school,
                    Integer.parseInt(entYearParam),
                    classNum,
                    isAttend);

        } else if (entYearParam != null && !entYearParam.isEmpty()) {

            studentList = studentDao.filter(
                    school,
                    Integer.parseInt(entYearParam),
                    isAttend);

        } else {

            studentList = studentDao.filter(
                    school,
                    isAttend);
        }

        List<Integer> entYearList =
                studentDao.getEntYearList(school.getCd());

        List<String> classList =
                classNumDao.filter(school.getCd());

        req.setAttribute("studentList", studentList);
        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classList", classList);
        req.setAttribute("selectedYear", entYearParam);
        req.setAttribute("selectedClass", classNum);
        req.setAttribute("isAttend", isAttend);

        req.getRequestDispatcher(
                "/scoremanager/main/student_list.jsp")
                .forward(req, res);
    }
}