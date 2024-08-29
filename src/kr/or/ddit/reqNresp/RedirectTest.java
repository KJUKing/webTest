package kr.or.ddit.reqNresp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirectTest.do")
public class RedirectTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
                -redirect방식
                1) 다른페이지로 단순 이동하는것을 뜻함 (Request객체와 Response객체를 공유하지않음)
                2) 응답시 브라우저에게 '이동할URL'을 전송하여 브라우저가 해당 URL로 재요청하여 이동하는 방식이다
                    -> 이동할 때 GET방식으로 요청하기때문에 URL길이에 제한이있다.

         */

        //Redirect방식은 Request객체를 공유하지 못한다.
        request.setAttribute("tel", "010-9999-8888");

        // Response객체의 sendRedirect()메소드를 이용하여 이동한다.
        // -> 형식) Response객체.sendRedirect("이동할 URL");
        //          '이동할URL'은 전체 URL주소로 지정해주어야한다
        //         -> URL경로에 한글이 포함되어 있으면 URLEncoder객체를 이용하여 인코딩해서 지정해줘야한다.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
