package scoremanager.main;

import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        StudentDao stuDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();

        req.setAttribute("ent_year_set", stuDao.getEntYearList(schoolCd));
        req.setAttribute("class_num_set", cDao.filter(schoolCd));
        req.setAttribute("subject_set", sDao.filter(schoolCd));

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
