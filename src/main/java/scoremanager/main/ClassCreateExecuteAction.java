package scoremanager.main;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import tool.Util;

public class ClassCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Util util = new Util();
        Teacher teacher = util.getUser(req);
        String schoolCd = teacher.getSchool().getCd();

        String classNum = req.getParameter("classNum");

        ClassNumDao dao = new ClassNumDao();
        dao.insert(schoolCd, classNum);

        req.getRequestDispatcher("class_create_done.jsp").forward(req, res);
    }
}
