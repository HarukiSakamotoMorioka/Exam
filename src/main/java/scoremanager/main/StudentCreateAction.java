package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        School school = teacher.getSchool();

        ClassNumDao classNumDao = new ClassNumDao();

        // 入学年度リスト
        List<Integer> entYearList = new ArrayList<>();

        int year = LocalDate.now().getYear();

        for (int i = year - 10; i <= year + 10; i++) {
            entYearList.add(i);
        }

        // クラスリスト
        List<String> classList = classNumDao.filter(school.getCd());

        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classList", classList);

        req.getRequestDispatcher("/scoremanager/main/student_create.jsp")
           .forward(req, res);
    }
}