package kr.or.ddit.reqNresp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/forwardTargetTest.do")
public class ForwardTargetTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        // Request객체와 Response객체를 공유하기 때문에 파라미터도 이곳에서 읽어올수있따.
        String username = request.getParameter("username");

        // 이전 문서에서 setAttribute()메소드로 세팅한 데이터 갖고오기
        String tel = (String) request.getAttribute("tel");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><meta charset=\"utf-8\"></head><title>forward연습 </title></head>");
        out.println("<body>");
        out.println("<h3> Forward로 이동한 결과 </h3>");
        out.println("<table border='1'>");
        out.println("<tr><td>이름</td><td>" + username + "</td></tr>");
        out.println("<tr><td>전화번호</td><td>" + tel + "</td></tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }
}
