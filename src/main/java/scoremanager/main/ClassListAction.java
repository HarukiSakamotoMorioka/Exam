package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import tool.Util;

public class ClassListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Util util = new Util();
        Teacher teacher = util.getUser(req);

        String schoolCd = teacher.getSchool().getCd();

        ClassNumDao dao = new ClassNumDao();
        List<String> list = dao.filter(schoolCd);

        req.setAttribute("classList", list);

        req.getRequestDispatcher("class_list.jsp").forward(req, res);
    }
}
