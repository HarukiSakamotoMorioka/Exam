package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // 入学年度リスト（今年±10年）
        int year = LocalDate.now().getYear();
        List<Integer> entYearList = new ArrayList<>();
        for (int y = year - 10; y <= year + 10; y++) {
            entYearList.add(y);
        }
        req.setAttribute("entYearList", entYearList);

        // セッションから Teacher を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        // クラスリスト（String のリスト）
        ClassNumDao cdao = new ClassNumDao();
        List<String> classList = cdao.filter(teacher.getSchool().getCd());
        req.setAttribute("classList", classList);

        req.getRequestDispatcher("/scoremanager/main/student_create.jsp")
           .forward(req, res);
    }
}
