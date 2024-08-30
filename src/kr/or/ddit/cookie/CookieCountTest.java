package kr.or.ddit.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookieCount.do")
public class CookieCountTest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();
        int cnt = 1;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cnt")) {
                    cnt = Integer.parseInt(cookie.getValue()) +1;
                }
            }
        }
        Cookie countCookie = new Cookie("cnt", Integer.toString(cnt));
        resp.addCookie(countCookie);

        out.println("<html>");
        out.println("<head><meta charset=\"utf-8\"><title>test</title></head>");
        out.println("<body>");
        out.println("<h1>어서오세요. 당신은 "+cnt+"번째 방문자입니다 </h1><br><br>");
        out.println("<a href='"+ req.getContextPath()+ "/cookieCount.do'>Cookie Count 증가하기</a>");
        out.println("<a href='"+ req.getContextPath()+ "/basic/cookie/cookieTest02.jsp'>시작 문서로 이동</a>");

        out.println("</body");
        out.println("</html>");

    }
}
