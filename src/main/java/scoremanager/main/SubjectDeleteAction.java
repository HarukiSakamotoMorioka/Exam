package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    String method = request.getMethod();

	    if (method.equals("GET")) {
	        // ▼ 削除確認画面の表示
	        String cd = request.getParameter("cd");

	        SubjectDao dao = new SubjectDao();
	        Subject subject = dao.find(cd);

	        request.setAttribute("subject", subject);
	        request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp")
	               .forward(request, response);

	    } else if (method.equals("POST")) {
	        // ▼ 削除実行
	        String cd = request.getParameter("cd");

	        SubjectDao dao = new SubjectDao();
	        dao.delete(cd);   // ← これが削除処理

	        // 完了画面へ
	        request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp")
	               .forward(request, response);
	    }
	}

}
