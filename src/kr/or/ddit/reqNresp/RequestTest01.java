package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.DoubleStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RequestTest01.do")
public class RequestTest01 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 전달되는 데이터의 문자 인코딩 방식
		request.setCharacterEncoding("utf-8");

		// 전달되는 데이터 받기

		// 1) Request객체.getParameter("파라미터명");
		//			==> 지정한 '파라미터명'에 설정된 '값'을 가져온다.
		//			-> 가져오는 값의 자료형은 String 이다
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// 형식2) Request객체.getParameterValues("파라미터명");
		//			-> '파라미터명'이 같은 것이 여러개일 경우에 사용
		//			-> 가져오는 값의 자료형은 String[] 임
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><meta charset=\"utf-8\"><title>Request객체연습</title></head>");
		out.println("<body>");
		out.println("<h2> 수신 받은 자료들 </h2>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");

		out.println("<tr><td>직업</td>");
		out.println("<td>" + job + "</td></tr>");

		out.println("<tr><td>취미</td>");
		out.println("<td>");
		if (hobbies != null) {
//			for (int i = 0; i < hobbies.length; i++) {
//				out.println("<tr><td>" + hobbies[i] + "</td>");
//				out.println(hobbies[i] + "<br>");
//			}
			for (String hobby : hobbies) {
				out.println(hobby + "<br>");
			}
		}
		out.println("</td></tr>");
		out.println("</table>");

		out.println("<hr>");

		out.println("<h2>HttpServletRequest객체의 메서드들~ </h2>");
		out.println("<ol>");
		out.println("<li>클라이언트의 IP주소 : " + request.getRemoteAddr() + "</li>");
		out.println("<li>요청 메소드" + request.getMethod() + "</li>");
		out.println("<li>ContextPath : " + request.getContextPath() + "</li>");
		out.println("<li>프로토콜 : " + request.getProtocol() + "</li>");
		out.println("<li>URL주소 : " + request.getRequestURL() + "</li>");
		out.println("<li>URI주소 : " + request.getRequestURI() + "</li>");
		out.println("</ol>");
		out.println("</html>");

		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}
}
