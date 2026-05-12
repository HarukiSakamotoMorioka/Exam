package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
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

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String schoolCd = teacher.getSchool().getCd();

        // 科目検索用
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        // 学生番号検索用
        String studentNo = request.getParameter("student_no");

        Map<String, String> errors = new HashMap<>();

        // 初期表示
        if (entYearStr == null && studentNo == null) {
            setForm(request, 0, "0", "0", "", errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // ============================
        // ① 学生番号検索
        // ============================
        if (studentNo != null && !studentNo.isEmpty()) {

            TestListStudentDao stuDao = new TestListStudentDao();
            List<TestListStudent> scores = stuDao.filterByStudentNo(schoolCd, studentNo);

            if (scores.isEmpty()) {
                errors.put("notfound", "成績情報が存在しませんでした");
                setForm(request, 0, "0", "0", studentNo, errors, teacher);
                request.getRequestDispatcher("test_list.jsp").forward(request, response);
                return;
            }

            request.setAttribute("scores", scores);
            setForm(request, 0, "0", "0", studentNo, errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // ============================
        // ② 科目検索
        // ============================

        int entYear = 0;
        if (entYearStr != null && entYearStr.matches("\\d+")) {
            entYear = Integer.parseInt(entYearStr);
        }

        if (entYear == 0 || "0".equals(classNum) || "0".equals(subjectCd)) {
            errors.put("input", "入学年度とクラスと科目を選択してください");
            setForm(request, entYear, classNum, subjectCd, "", errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        TestListStudentDao stuDao = new TestListStudentDao();
        TestListSubjectDao subDao = new TestListSubjectDao();

        List<TestListStudent> students = stuDao.filter(schoolCd, entYear, classNum);

        if (students.isEmpty()) {
            errors.put("notfound", "学生情報が存在しませんでした");
            setForm(request, entYear, classNum, subjectCd, "", errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        List<TestListSubject> scores = subDao.filter(schoolCd, subjectCd, entYear, classNum);

        if (scores.isEmpty()) {
            errors.put("notfound", "成績情報が存在しませんでした");
            setForm(request, entYear, classNum, subjectCd, "", errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        request.setAttribute("scores", scores);
        setForm(request, entYear, classNum, subjectCd, "", errors, teacher);
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }

    private void setForm(HttpServletRequest request, int entYear,
            String classNum, String subjectCd, String studentNo,
            Map<String, String> errors, Teacher teacher) throws Exception {

        String schoolCd = teacher.getSchool().getCd();

        StudentDao stuDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();

        // ★ DB から入学年度一覧を取得
        request.setAttribute("ent_year_set",
                stuDao.getEntYearList(schoolCd));

        // ★ DB からクラス番号一覧を取得
        request.setAttribute("class_num_set",
                cDao.filter(schoolCd));

        // ★ DB から科目一覧を取得
        request.setAttribute("subject_set",
                sDao.filter(schoolCd));

        // 入力値保持
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("student_no", studentNo);

        request.setAttribute("errors", errors);
    }

}
