package scoremanager.main;

import java.util.List;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {   // ← Action を継承する

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {   // ← Action と同じシグネチャ

        // DAO 呼び出し
        SubjectDao dao = new SubjectDao();
        List<Subject> subjects = dao.findAll();

        // JSP に渡す
        req.setAttribute("subjects", subjects);

        // JSP へフォワード
        req.getRequestDispatcher("/scoremanager/main/subjectlist.jsp")
           .forward(req, res);
    }
}
