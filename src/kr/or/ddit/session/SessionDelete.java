package kr.or.ddit.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //session삭제

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        // Session 삭제법
        // 1) Session객체.removeAttribute("삭제할 key값"); 개별적인 세션 삭제
        //session.removeAttribute("testSession");
        // 2) Session객체.invalidate(); Session자체 삭제
        session.invalidate();

        out.println("<html>");
        out.println("<head><meta charset=\"UTF-8\"><title>Session연습</title></head>");
        out.println("<body>");

        out.println("<h3>Session정보 삭제하기</h3>");


        out.println("<a href='" + req.getContextPath() +
                "/basic/session/sessionTest.jsp'>시작 문서로 이동</a>");


        out.println("</body>");
        out.println("</html>");

    }
}
