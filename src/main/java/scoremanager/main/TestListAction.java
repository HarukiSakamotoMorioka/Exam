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
            response.sendRedirect(request.getContextPath() + "/login.jsp");
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

        // ★ 初期表示（検索フォームだけ表示）
        if (entYearStr == null && studentNo == null) {
            setForm(request, 0, "0", "0", "", errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // ============================
        // ① 学生番号検索
        // ============================
        if (studentNo != null && !studentNo.trim().isEmpty()) {

            TestListStudentDao stuDao = new TestListStudentDao();
            List<TestListStudent> scores = stuDao.filterByStudentNo(schoolCd, studentNo.trim());

            if (scores.isEmpty()) {
                errors.put("notfound", "成績情報が存在しませんでした");
                setForm(request, 0, "0", "0", studentNo, errors, teacher);
                request.getRequestDispatcher("test_list.jsp").forward(request, response);
                return;
            }

            // ★ 学生番号検索 → test_list.jsp に forward
            request.setAttribute("scores", scores);
            request.setAttribute("mode", "student");
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

        // 入力チェック
        if (entYear == 0 || "0".equals(classNum) || "0".equals(subjectCd)) {
            errors.put("input", "入学年度とクラスと科目を選択してください");
            setForm(request, entYear, classNum, subjectCd, "", errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        TestListSubjectDao subDao = new TestListSubjectDao();
        List<TestListSubject> scores = subDao.filter(schoolCd, subjectCd, entYear, classNum);

        if (scores.isEmpty()) {
            errors.put("notfound", "成績情報が存在しませんでした");
            setForm(request, entYear, classNum, subjectCd, "", errors, teacher);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // ★ 科目検索 → test_list.jsp に forward
        request.setAttribute("scores", scores);
        request.setAttribute("mode", "subject");
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

        request.setAttribute("ent_year_set", stuDao.getEntYearList(schoolCd));
        request.setAttribute("class_num_set", cDao.filter(schoolCd));
        request.setAttribute("subject_set", sDao.filter(schoolCd));

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("student_no", studentNo);

        request.setAttribute("errors", errors);
    }

}
