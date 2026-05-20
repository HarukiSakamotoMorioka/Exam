package scoremanager.main;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
            return;
        }

        School school = teacher.getSchool();
        String schoolCd = school.getCd();

        // 入学年度一覧
        StudentDao studentDao = new StudentDao();
        List<Student> allStudents = studentDao.filter(school, false);
        Set<Integer> entYearSet = new TreeSet<>();
        for (Student s : allStudents) entYearSet.add(s.getEntYear());
        req.setAttribute("ent_year_set", entYearSet);

        // クラス一覧
        ClassNumDao classNumDao = new ClassNumDao();
        req.setAttribute("class_num_set", classNumDao.filter(schoolCd));

        // 科目一覧
        SubjectDao subjectDao = new SubjectDao();
        req.setAttribute("subject_set", subjectDao.findAll());

        // 回数一覧
        Set<Integer> numSet = new TreeSet<>();
        for (int i = 1; i <= 10; i++) numSet.add(i);
        req.setAttribute("numSet", numSet);

        // 検索条件
        String entYearStr = req.getParameter("entYear");
        String classNum   = req.getParameter("classNum");
        String subjectCd  = req.getParameter("subjectCd");
        String numStr     = req.getParameter("num");

        boolean searched =
            entYearStr != null && !entYearStr.isEmpty() &&
            classNum   != null && !classNum.isEmpty() &&
            subjectCd  != null && !subjectCd.isEmpty() &&
            numStr     != null && !numStr.isEmpty();

        if (searched) {
            int entYear = Integer.parseInt(entYearStr);
            int num     = Integer.parseInt(numStr);

            Subject subject = subjectDao.find(schoolCd, subjectCd);
            List<Student> testListStudent =
                studentDao.filter(school, entYear, classNum, false);

            req.setAttribute("testListStudent", testListStudent);
            req.setAttribute("subject", subject);
            req.setAttribute("num", num);
        }

        req.setAttribute("selEntYear",   entYearStr);
        req.setAttribute("selClassNum",  classNum);
        req.setAttribute("selSubjectCd", subjectCd);
        req.setAttribute("selNum",       numStr);

        req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
           .forward(req, res);
    }
}
