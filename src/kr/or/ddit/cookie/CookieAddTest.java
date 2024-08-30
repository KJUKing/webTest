package kr.or.ddit.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookieAdd.do")
public class CookieAddTest extends HttpServlet {

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

        // Cookie 저장하는 방법

        // 1. Cookie객체를 생성한다 -> '쿠키이름'과 '쿠키값'을 문자열로 지정하여 생성한다.
        //형식) Cookie 변수명 = new Cookie("쿠키이름", "쿠키값"
        //쿠키 값으로 한글을 사용할 경우에는 URLEncoder.encode()메소드로 인코딩한 후 저장한다
        // 하나의 쿠키는 4kb(4096바이트)
        // 하나의 도메인 당 20개 (총300개)까지 저장가능

        Cookie name = new Cookie("name", "홍길동");
        Cookie age = new Cookie("age", "18");
        Cookie gender = new Cookie("gender", "male");

        // 2.쿠키 속성을 설정한다
        // 쿠키변수.setPath("적용경로"); -> 지정한 경로와 그 하위경로에서만 사용 가능하다.
//                                    -> 생략하면 쿠키를 저장할 당시의 경로가 설정된다
        // 쿠키변수.setMaxAge(유지시간); -> 단위(초)
        //                               설정안하면 -1로설정되고 브라우저가 종료될때까지 유지됨
        //                               0으로 설정하면 즉시삭제됨
        // 쿠키변수.setDomain("적용도메인명"); -> 예) ".ddit.or.kr"
        //                                      www.ddit.or.kr, mail.ddit.or.kr, cafe.ddit.or.kr
        // 쿠키변수.setSecure(보안여부) //true적용 false미적용(기본값)
        //                           // 'https'프로토콜에서는 true값으로 설정해야 사용할 수 있다.
        // 3. Response객체를 이용하여 쿠키를 웹브라우저로 보내면 웹브라우저가 쿠키를 받아서 저장함
        // 형식) Response객체.addCookie(1번에서만든 cookie객체);

        resp.addCookie(name);
        resp.addCookie(age);
        resp.addCookie(gender);
        out.println("<html>");
        out.println("<head><meta charset=\"utf-8\"><title>Cookie 저장 연습</title></head>");
        out.println("<body>");
        out.println("<h3>Cookie 데이터가 저장되었습니다 </h3><br><br>");
        out.println("<a href='"+ req.getContextPath()+ "/basic/cookie/cookieTest01.jsp'>시작 문서로 이동</a>");

        out.println("</body");
        out.println("</html>");

    }
}
