package scoremanager.main;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import tool.Util;

public class ClassUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Util util = new Util();
        Teacher teacher = util.getUser(req);
        String schoolCd = teacher.getSchool().getCd();

        String oldNum = req.getParameter("oldNum");
        String newNum = req.getParameter("newNum");

        ClassNumDao dao = new ClassNumDao();
        dao.update(schoolCd, oldNum, newNum);

        req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
    }
}
