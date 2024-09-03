package kr.or.ddit.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

@WebServlet("/sessionLoginDb.do")
public class SessionLoginDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(request.getParameter("id"));
		memVo.setMem_pass(request.getParameter("pass"));
		
		SessionLoginService service = SessionLoginService.getInstance();
		MemberVO resVo = service.getLoginMember(memVo);
		
		HttpSession session = request.getSession();
		if(resVo.getMem_id() != null) session.setAttribute("resVo", resVo);
		
		response.sendRedirect(request.getContextPath()+"/basic/session/sessionLoginDb.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
