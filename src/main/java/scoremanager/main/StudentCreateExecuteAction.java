package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = req.getParameter("ent_year");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("class_num");

        boolean hasError = false;

        // 入学年度チェック
        if (entYearStr == null || entYearStr.isEmpty()) {
            req.setAttribute("entYearError", "入学年度を選択してください");
            hasError = true;
        }

        // 学生番号チェック
        if (no == null || no.isEmpty()) {
            req.setAttribute("noError", "学生番号を入力してください");
            hasError = true;
        }

        // 氏名チェック
        if (name == null || name.isEmpty()) {
            req.setAttribute("nameError", "氏名を入力してください");
            hasError = true;
        }

        StudentDao dao = new StudentDao();

        // 学生番号重複チェック
        if (no != null && !no.isEmpty()) {

            Student old = dao.get(no);

            if (old != null) {
                req.setAttribute("noDupError", "学生番号が重複しています");
                hasError = true;
            }
        }

        // エラーがある場合
        if (hasError) {

            StudentCreateAction action = new StudentCreateAction();
            action.execute(req, res);

            return;
        }

        // 登録処理
        Student student = new Student();

        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        dao.save(student);

        req.getRequestDispatcher("/scoremanager/main/student_create_done.jsp")
        .forward(req, res);    }
}