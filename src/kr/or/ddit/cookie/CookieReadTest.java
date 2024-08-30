package kr.or.ddit.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//쿠키 정보ㅡㄹ 저장하는 서블릿
@WebServlet("/cookieRead.do")
public class CookieReadTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();

        // 저장된 쿠키 읽어오기

        // 1. 전체 쿠키 정보들은 배열에 저장된다
        //형식) Cookie[] cookieArr = request.getCookies();
        Cookie[] cookies = req.getCookies();

        out.println("<html>");
        out.println("<head><meta charset = 'utf-8'>Cookie읽기연습</head> ");
        out.println("<body>");

        out.println("<h2>저장된 Cookie데이터확인하기</h2>");

        if (cookies == null || cookies.length == 0) {
            out.println("<h3> 저장된 쿠키가 하나도 없습니다</h3>");

        } else {
            for (Cookie cookie : cookies) {
                //쿠키 이름 갖고오기
                String name = cookie.getName();
                String value = cookie.getValue();
                // 쿠키값 가져오기 -> 저장된 쿠키값이 한글일경우에는 URLDecoder.decode()메소드 이용하여
                //                      디코딩한 후 에 사용한다
                out.print("name : " + name + "<br>");
                out.print("value : " + value + "<hr>");
            }
            out.println("<a href='"+ req.getContextPath()+ "/basic/cookie/cookieTest01.jsp'>시작 문서로 이동</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
