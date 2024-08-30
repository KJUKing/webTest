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
@WebServlet("/cookieDelete.do")
public class CookieDeleteTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();

        // 저장된 쿠키 삭제

        // 1. 전체 쿠키 정보가져오기
        //형식) Cookie[] cookieArr = request.getCookies();
        Cookie[] cookieArr = req.getCookies();

        out.println("<html>");
        out.println("<head><meta charset = 'utf-8'>Cookie 삭제연습</head>");
        out.println("<body>");

        out.println("<h3>Cookie데이터 삭제하기</h2>");
        //쿠키배열에서 삭제하려는 쿠키 구해오기

        if (cookieArr == null || cookieArr.length == 0) {
            out.println("<h3> 저장된 쿠키가 하나도 없습니다 </h3>");
        } else {
            for (Cookie cookie : cookieArr) {
                //쿠키 이름 갖고오기
                String name = cookie.getName();

                if("gender".equals(name)) {
                    //3. 찾은 쿠키의 유지시간을 0으로 설정한 후 다시 저장한다
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }

            }   
        }
        out.println("<a href='"+ req.getContextPath()+ "/basic/cookie/cookieTest01.jsp'>시작 문서로 이동</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
