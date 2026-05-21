package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import tool.Util;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Util util = new Util();
        Teacher teacher = util.getUser(req);

        String subjectCd = req.getParameter("subjectCd");
        int num = Integer.parseInt(req.getParameter("num"));
        String classNum = req.getParameter("classNum");

        String[] studentNos = req.getParameterValues("studentNo");

        List<Test> list = new ArrayList<>();

        for (String stuNo : studentNos) {
            String pointStr = req.getParameter("point_" + stuNo);

            if (pointStr == null || pointStr.isEmpty()) continue;

            int point = Integer.parseInt(pointStr);

            Test t = new Test();

            // ★ Student を setter で作る
            Student stu = new Student();
            stu.setNo(stuNo);
            t.setStudent(stu);

            // ★ Subject も setter で作る
            Subject sub = new Subject();
            sub.setCd(subjectCd);
            t.setSubject(sub);

            // ★ School は teacher から
            t.setSchool(teacher.getSchool());

            t.setNo(num);
            t.setPoint(point);
            t.setClassNum(classNum);

            list.add(t);
        }


        TestDao dao = new TestDao();
        dao.insert(list);

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
