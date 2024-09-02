package kr.or.ddit.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        // 1. Session객체를 생성하거나 현재 Session 가져오기
        HttpSession session = req.getSession();

        // 2. Session객체에 setAttribute()메소드로 저장한 값을
//                           setAttribute()메소드를 이용하여 가져온다
//                  -> Session객체.getAttribute("키값");
        out.println("<html>");
        out.println("<head><meta charset=\"UTF-8\"><title>Session연습</title></head>");
        out.println("<body>");
        // Session값 가져오기

        String sessionValue = (String) session.getAttribute("testSession");

        if (sessionValue != null) {
            out.print("testSession의 세션값 : " + sessionValue);
        } else {
            out.print("<h3>testSession의 값이 없습니다</h3>");
        }
        out.println("<hr>");

        out.println("<h3>전체 Session정보 확인하기</h3>");
        out.println("<ol>");

        // Session 전체값 갖고오기
        Enumeration<String> sessionNames = session.getAttributeNames();

        int cnt = 0;
        while (sessionNames.hasMoreElements()) {
            cnt++;
            String key = sessionNames.nextElement();
            out.println("<li>" + key + "=" + session.getAttribute(key) + "</li>");
        }

        if (cnt == 0) {
            out.println("<li>Session 정보가 하나도 없습니다</li>");
        }
        out.println("</ol>");
        out.println("<hr>");

        out.println("세션 Id : " + session.getId() + "<br>");

        //생성시간 -> 1970년 1월 1일부터 경과한시간(밀리세컨트)
        out.println("세션 생성 시간 : " + session.getCreationTime() + "<br>");

        out.println("세션 최근 접근 시간 : " + session.getLastAccessedTime() + "<br>");

        // 세션 유효 시간 -> 단위 (초)
        out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
        



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
