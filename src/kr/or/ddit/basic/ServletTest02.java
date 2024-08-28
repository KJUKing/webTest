package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	이 예제는 애노테이션(@WebServlet)를 이용하여 Servlet을 등록하고 처리하는 예제
	이 애노테이션(@WebServlet)은 Servlet버전 3.0이상에서 사용할 수 있다.


	@WebServlet애노테이션의 속성들
	1) name : 서블릿의 이름을 설정한다. (기본값 : 빈문자열(""))
	2) urlPatterns : 서블릿의 URL패턴의 목록을 설정한다. (기본값 : 빈배열( {} ) )
	   예) urlPatterns="/url1" 또는 urlPatterns={"/url1"}  ==> 패턴이 1개일 경우
	   예) urlPatterns={ "/url1", "/url2", ....}  ==> 패턴이 2개 이상일 경우
	3) value : urlPartterns와 동일한 기능을 한다.
	4) description : 주석(설명글)을 설정한다.
*/

//@WebServlet(
//	urlPatterns = "/servletTest02.ddit",
//	description = "애노테이션을 이용한 서블릿 등록 연습용"
//)
@WebServlet("/servletTest02.ddit")
public class ServletTest02 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 처리한 내용 출력하기
		// 방법2) print()메서드, println()메서드, printf()메서드 이용하기
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>두번째 Servlet</title></head>");
		out.println("<body>");
		out.println("<h1 style='text-align:center; color:blue; '>");
		out.println("안녕하세요 두번째 Servlet입니다. (애노테이션 이용한 예제)");
		out.println("</h1>");
		out.println("</body></html>");
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}










