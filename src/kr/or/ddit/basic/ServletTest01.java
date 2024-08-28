package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	서블릿이란 ? ==> 컨테이너(서블릿 엔진)에 의해 관리되는 자바 기반 웹 컴포넌트로서
	 				동적인 웹 컨텐츠 생성을 가능하게 해준다.
	 				
	전체URL주소 ==>  http://localhost:8080/webTest/servletTest01.do
	- http : 프로토콜
	- localhost : 컴퓨터이름(도메인명) 또는 IP주소
	- 8080 : Port번호 ( port번호가 80번일 경우에는 생략 가능하다. )
	- /webTest : 컨텍스트패스 (보통 웹프로젝트 이름이 사용된다. )
	- /servletTest01.do  :  서블릿 요청 URL 패턴
	
	   				
*/



// 이 예제는 배포서술자(DD, Deployment Descriptor ==> web.xml)를 이용해서
// 실행할 Servlet을 등록하여 처리하는 예제이다.

// Servlet클래스는 HttpServlet클래스를 상속해서 작성한다.
public class ServletTest01 extends HttpServlet {
	/*
		- Servlet클래스에서는  service()메서드 또는 doGet()메서드, doPost()메서드를 재정의해서 작성한다.
		
		-  doGet()메서드나 doPost()메서드는 service()메서드를 통해서 호출된다.
		
		- 메서드의 매개변수로 주어지는 객체
		1) HttpServletRequest객체 	==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
		2) HttpServletResponse객체  	==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	*/
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");	// 응답 문서의 인코딩 방식 지정
		response.setContentType("text/html; charset=utf-8");  // 응답 문서의 ContentType 지정
		
		// 처리한 내용을 응답으로 보내기 위해 PrintWriter객체(스트림객체)를 생성한다.
		PrintWriter out = response.getWriter();  
		
		// 처리한 내용을 출력한다. ( ==>  클라이언트에게 처리된 결과를 보내준다. )
		// 방법1 : append()메서드 이용하기
		out.append("<html>")
			.append("<head>")
			.append("<meta charset='utf-8'>")
			.append("<title>첫번째 Servlet 문서</title>")
			.append("</head>")
			.append("<body>")
			.append("<h1 style='text-align:center;'>안녕하세요. 첫번째 Servlet입니다.</h1>")
			.append("</body>")
			.append("</html>");
		
	}
	
	// doPost()메서드 ==> POST방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
	
	
}
