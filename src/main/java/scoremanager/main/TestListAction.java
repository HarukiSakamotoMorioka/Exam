package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // teacher が null の場合はログインへ
        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String school = teacher.getSchool().getCd();

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        Map<String, String> errors = new HashMap<>();

        int entYear = 0;
        if (entYearStr != null && entYearStr.matches("\\d+")) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 入力チェック
        if (entYear == 0 || classNum == null || "0".equals(classNum)
                || subjectCd == null || "0".equals(subjectCd)) {

            errors.put("input", "入学年度とクラスと科目を選択してください");
            setForm(request, entYear, classNum, subjectCd, errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // DAO
        TestListStudentDao stuDao = new TestListStudentDao();
        TestListSubjectDao subDao = new TestListSubjectDao();

        // ① 学生が存在するかチェック
        List<TestListStudent> students = stuDao.filter(school, entYear, classNum);

        if (students.isEmpty()) {
            errors.put("notfound", "学生情報が存在しませんでした");
            setForm(request, entYear, classNum, subjectCd, errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // ② 成績を取得
        List<TestListSubject> scores = subDao.filter(school, subjectCd, entYear, classNum);

        if (scores.isEmpty()) {
            errors.put("notfound", "成績情報が存在しませんでした");
            setForm(request, entYear, classNum, subjectCd, errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // 正常 → 成績一覧を表示
        setForm(request, entYear, classNum, subjectCd, errors, teacher);
        request.setAttribute("scores", scores);

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }

    private void setForm(HttpServletRequest request, int entYear,
            String classNum, String subjectCd,
            Map<String, String> errors, Teacher teacher) throws Exception {

        LocalDate today = LocalDate.now();
        int year = today.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = 0; i < 10; i++) entYearSet.add(year - i);

        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();

        // ★ JSP と完全一致（f1 / f2 / f3）
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);

        // ★ ClassNumDao.filter(School) を正しく呼べる
        request.setAttribute("class_num_set",
        	    cDao.filter(teacher.getSchool()));

        request.setAttribute("subject_set", sDao.filter(teacher.getSchool().getCd()));
        request.setAttribute("ent_year_set", entYearSet);

        request.setAttribute("errors", errors);
    }
}
