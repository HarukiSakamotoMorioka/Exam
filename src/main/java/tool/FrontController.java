package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */

@WebServlet("*.action")
public class FrontController extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		//パスを取得
    		String path = request.getServletPath().substring(1);
    		//ファイル名を取得しクラス名に変換
    		String name = path.replace(".action","Action").replace('/','.');
    		
    		System.out.println("★ servlet path ->" + request.getServletPath());
    		System.out.println("★ class name ->" + name);
    		
    		//アクションクラスのインスタンスを返却
    		Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
    		
    		//遷移先URLを取得
    		action.execute(request,response);
    		//String url = action.excecute(request,response);
    		//request.getRequestDispatcher(url).foward(request,response);
    	
    	} catch (Exception e) {
    		e.printStackTrace();
    		//エラーページへリダイレクト
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
