package kr.or.ddit.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet ("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Session 정보를 저장하는 방법

        // 1. Session객체를 생성하거나 현재 Session 가져오기
        // 형식1) Request객체.getSession(); 또는 Request객체.getSession(true);
        // => 현재 Session이 존재하면 현재 Session을 반환하고, 존재하지 않으면 새로운 Sesssion을 생성한다
        // 형식2) Request객체.getSession(false);
        // => 현재 Session이 존재하면 현재 Session을 반환하고, 존재하지 않으면 null반환

        HttpSession session = req.getSession();

        // 2. Session객체의 setAttribute() 메소드를 이용하여 Session값을 저장한다
        // 형식) Session객체.setAttribute("key값", session값);
        // => 'key'값은 문자열. 'session'값은 모든 종류의 데이터

        session.setAttribute("testSession", "연슶용 sessionValue");
        session.setAttribute("userName", "홍길동");
        session.setAttribute("age", 30);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><meta charset=\"UTF-8\"><title>session연습 </title></head>");
        out.println("<body>");

        out.println("<a href='" + req.getContextPath() +
                "/basic/session/sessionTest.jsp'>시작 문서로 이동</a>");
        out.println("</body>");
        out.println("</html>");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
