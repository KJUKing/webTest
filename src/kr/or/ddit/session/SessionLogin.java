package kr.or.ddit.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String id = req.getParameter("id");
        String pass = req.getParameter("pass");

        String url = "/basic/session/sessionLogin.jsp";


        if ("admin".equals(id) && "1234".equals(pass)) {
            session.setAttribute("id", id);
            session.setAttribute("loginMessage", null);
            session.removeAttribute("inputId");

        } else {
            session.setAttribute("loginMessage", "로그인 실패");
            session.removeAttribute("id");
            session.setAttribute("inputId", id); //실패했을때id값
        }
        resp.sendRedirect(req.getContextPath() + url);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
