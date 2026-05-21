package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        String studentNo = req.getParameter("student_no");

        if (studentNo == null || studentNo.trim().isEmpty()) {
            req.setAttribute("error", "学生番号を入力してください");
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        StudentDao stuDao = new StudentDao();
        Student student = stuDao.get(studentNo.trim());

        if (student == null) {
            req.setAttribute("error", "学生情報が存在しませんでした");
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        TestListStudentDao dao = new TestListStudentDao();
        List<TestListStudent> list = dao.filterByStudentNo(schoolCd, studentNo.trim());

        // ★ 初期表示セット（必須）
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        req.setAttribute("ent_year_set", stuDao.getEntYearList(schoolCd));
        req.setAttribute("class_num_set", cDao.filter(schoolCd));
        req.setAttribute("subject_set", sDao.filter(schoolCd));

        req.setAttribute("student", student);
        req.setAttribute("scores", list);
        req.setAttribute("mode", "student");

        // ★ test_list_student.jsp ではなく test_list.jsp に戻す
        req.getRequestDispatcher("test_list.jsp").forward(req, res);

    }
}
