package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String oldNum = req.getParameter("oldNum");
        req.setAttribute("oldNum", oldNum);

        req.getRequestDispatcher("class_update.jsp").forward(req, res);
    }
}
