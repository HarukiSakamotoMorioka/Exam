package scoremanager.main;

import java.util.List;

import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        int entYear = Integer.parseInt(req.getParameter("f1"));
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        if (entYear == 0 || "0".equals(classNum) || "0".equals(subjectCd)) {
            req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> list = dao.filter(schoolCd, subjectCd, entYear, classNum);

        // ★ 初期表示セット（必須）
        StudentDao stuDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        req.setAttribute("ent_year_set", stuDao.getEntYearList(schoolCd));
        req.setAttribute("class_num_set", cDao.filter(schoolCd));
        req.setAttribute("subject_set", sDao.filter(schoolCd));

        req.setAttribute("scores", list);
        req.setAttribute("mode", "subject");

        // ★ test_list_student.jsp ではなく test_list.jsp に戻す
        req.getRequestDispatcher("test_list.jsp").forward(req, res);

    }
}
