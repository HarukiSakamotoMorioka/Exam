package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String classNum = req.getParameter("classNum");
        req.setAttribute("classNum", classNum);

        req.getRequestDispatcher("class_delete.jsp").forward(req, res);
    }
}
