package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // パスを取得
            String path = request.getServletPath().substring(1);

            // ファイル名を取得しクラス名に変換
            String name = path.replace(".action", "Action").replace('/', '.');

            System.out.println("★ servlet path -> " + request.getServletPath());
            System.out.println("★ class name -> " + name);

            // アクションクラスのインスタンスを返却
            Action action = (Action) Class.forName(name)
                    .getDeclaredConstructor()
                    .newInstance();

            // 遷移先URLを取得（Action 内で forward する）
            action.execute(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

