package kr.or.ddit.cookie;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String check = request.getParameter("check");

		manageCookie(response, id, check);

		String url = "/basic/cookie/cookieLogin.jsp";

		if ("test".equals(id) && "1234".equals(pass)) {
			url = "/basic/cookie/cookieMain.jsp";
		} else {
			url = "/basic/cookie/cookieLogin.jsp?error=invalid";
		}
		response.sendRedirect(request.getContextPath() + url);
	}

	private void manageCookie(HttpServletResponse response, String id, String check) {
		Cookie idCookie = new Cookie("id", id != null ? id : "");
		if (check != null && id != null) {
			idCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유효
		} else {
			idCookie.setMaxAge(0); // 쿠키 삭제
		}
		response.addCookie(idCookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
