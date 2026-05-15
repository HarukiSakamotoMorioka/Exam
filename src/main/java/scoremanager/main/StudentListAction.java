package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションからログインユーザーを取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 変数の初期化
        String entYearStr = "";
        String classNum = "";
        String isAttendStr = "";
        int entYear = 0;
        boolean isAttend = false;
        List<Student> students = null;

        // 現在の年を取得
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        // Dao初期化
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        // エラーメッセージ
        Map<String, String> errors = new HashMap<>();

        // 1. リクエストパラメーターの取得
        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        isAttendStr = request.getParameter("f3");

        // 2. 文字列を適切な型に変換（フィルター処理の前に実施）
        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (isAttendStr != null) {
            isAttend = true;
        }

        // 3. DBからデータ取得
        List<String> list = cNumDao.filter(teacher.getSchool().getCd());

        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            // 入学年度とクラス番号を指定
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && (classNum == null || classNum.equals("0"))) {
            // 入学年度のみ指定
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            // 指定なし：全学生情報を取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            // クラスのみ指定された場合はエラー
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            request.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // 4. 入学年度セットを作成（現在の年から過去10年分）
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entYearSet.add(year - i);
        }

        // 5. リクエストに値をセット
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        if (isAttendStr != null) {
            request.setAttribute("f3", isAttendStr);
        }
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", list);
        request.setAttribute("ent_year_set", entYearSet);

        // 6. JSPへフォワード
        request.getRequestDispatcher("student_list.jsp").forward(request, response);
    }
}