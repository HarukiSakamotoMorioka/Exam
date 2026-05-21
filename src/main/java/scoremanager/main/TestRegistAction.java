package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import tool.Util;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Util util = new Util();
        Teacher teacher = util.getUser(req);
        
        // ▼ 初期表示セット（Util）
        util.setEntYearSet(req);
        util.setClassNumSet(req);
        util.setSubjects(req);
        util.setNumSet(req);

        // ▼ 検索条件取得
        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectCd");
        String num = req.getParameter("num");

        // 初期表示（検索前）
        if (entYear == null && classNum == null && subjectCd == null && num == null) {
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        // 入力チェック
        if (entYear.isEmpty() || classNum.isEmpty() || subjectCd.isEmpty() || num.isEmpty()) {
            req.setAttribute("errors", "すべての項目を選択してください");
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        // ▼ 学生一覧取得（TestListStudentDao ではなく StudentDao）
        StudentDao stuDao = new StudentDao();
        List<Student> list = stuDao.filter(
                teacher.getSchool(),
                Integer.parseInt(entYear),
                classNum,
                true   // 出席している学生のみ
        );

        // JSP に渡す
        req.setAttribute("studentList", list);


        req.setAttribute("testListStudent", list);

        // ▼ 選択状態を保持
        req.setAttribute("selEntYear", entYear);
        req.setAttribute("selClassNum", classNum);
        req.setAttribute("selSubjectCd", subjectCd);
        req.setAttribute("selNum", num);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
