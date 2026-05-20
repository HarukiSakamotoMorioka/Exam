package scoremanager.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

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

        // ▼ パラメータ取得
        String entYearStr = req.getParameter("entYear");
        String classNum   = req.getParameter("classNum");
        String subjectCd  = req.getParameter("subjectCd");
        String numStr     = req.getParameter("num");

        int entYear = Integer.parseInt(entYearStr);
        int num     = Integer.parseInt(numStr);

        // ▼ 科目取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.find(schoolCd, subjectCd);

        // ▼ 学生一覧取得
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.filter(school, entYear, classNum, false);

        boolean hasError = false;
        List<Test> testList = new ArrayList<>();

        // ▼ 入力チェック & Test作成
        for (Student student : students) {

            String pointStr = req.getParameter("point_" + student.getNo());
            if (pointStr == null || pointStr.trim().isEmpty()) continue;

            try {
                int point = Integer.parseInt(pointStr.trim());

                if (point < 0 || point > 100) {
                    hasError = true;
                } else {

                    Test test = new Test();

                    // 学生
                    Student st = new Student();
                    st.setNo(student.getNo());
                    test.setStudent(st);

                    // 科目
                    Subject sub = new Subject();
                    sub.setCd(subjectCd);
                    test.setSubject(sub);

                    // 学校
                    School sc = new School();
                    sc.setCd(schoolCd);
                    test.setSchool(sc);

                    // その他
                    test.setNo(num);
                    test.setPoint(point);
                    test.setClassNum(classNum);

                    // ★★★ これが無いと登録されない ★★★
                    testList.add(test);
                }

            } catch (NumberFormatException e) {
                hasError = true;
            }
        }

        // ▼ エラー時：元の画面へ戻す
        if (hasError) {

            List<Student> allStudents = studentDao.filter(school, true);
            Set<Integer> entYearSet = new TreeSet<>();
            for (Student s : allStudents) entYearSet.add(s.getEntYear());
            req.setAttribute("ent_year_set", entYearSet);

            ClassNumDao classNumDao = new ClassNumDao();
            req.setAttribute("class_num_set", classNumDao.filter(schoolCd));

            req.setAttribute("subject_set", subjectDao.filter(schoolCd));

            Set<Integer> numSet = new TreeSet<>();
            for (int i = 1; i <= 10; i++) numSet.add(i);
            req.setAttribute("numSet", numSet);

            req.setAttribute("testListStudent", students);
            req.setAttribute("subject",         subject);
            req.setAttribute("num",             num);
            req.setAttribute("selEntYear",      entYearStr);
            req.setAttribute("selClassNum",     classNum);
            req.setAttribute("selSubjectCd",    subjectCd);
            req.setAttribute("selNum",          numStr);
            req.setAttribute("errorMessage",    "0〜100の範囲で入力してください");

            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
               .forward(req, res);
            return;
        }

        // ▼ 正常時：DB登録
        TestDao testDao = new TestDao();
        for (Test test : testList) {
            testDao.insert(test);
        }

        // ▼ 完了画面へ
        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
           .forward(req, res);
    }
}
