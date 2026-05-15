package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // ▼ パラメータ取得
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String entYearStr = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");

        Map<String, String> errors = new HashMap<>();

        // ▼ 必須チェック
        if (entYearStr == null || entYearStr.isEmpty()) {
            errors.put("entYearError", "入学年度を選択してください");
        }
        if (no == null || no.isEmpty()) {
            errors.put("noError", "学生番号を入力してください");
        }
        if (name == null || name.isEmpty()) {
            errors.put("nameError", "氏名を入力してください");
        }

        // ▼ 数値変換（入学年度）
        int entYear = 0;
        if (!errors.containsKey("entYearError")) {
            entYear = Integer.parseInt(entYearStr);
        }

        // ▼ 重複チェック（学生番号が入力されている場合のみ）
        StudentDao dao = new StudentDao();
        if (!errors.containsKey("noError") && dao.get(no) != null) {
            errors.put("noDupError", "学生番号が重複しています");
        }

        // ▼ エラーがある場合 → 登録画面へ戻す
        if (!errors.isEmpty()) {

            // エラーを JSP に渡す
            for (Map.Entry<String, String> e : errors.entrySet()) {
                req.setAttribute(e.getKey(), e.getValue());
            }

            // 入力値を保持
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("ent_year", entYearStr);
            req.setAttribute("class_num", classNum);

            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        // ▼ ログイン中の教師から school を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        // ▼ 学生情報をセット
        Student stu = new Student();
        stu.setNo(no);
        stu.setName(name);
        stu.setEntYear(entYear);
        stu.setClassNum(classNum);
        stu.setAttend(true);
        stu.setSchool(teacher.getSchool());

        // ▼ DB登録
        dao.save(stu);

        // ▼ 登録完了画面へ
        req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
    }
}
