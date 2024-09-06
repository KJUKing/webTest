package kr.or.ddit.memberList.controller;

import kr.or.ddit.memberList.dao.IMemberService;
import kr.or.ddit.memberList.dao.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/memberDetail.do")
public class MemberDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");

        IMemberService service = MemberServiceImpl.getInstance();
        MemberVO member = service.memberDetail(id);

        // 회원 정보를 request 객체에 저장
        req.setAttribute("member", member);

        // memberDetail.jsp로 포워딩
        req.getRequestDispatcher("/basic/memberList/memberDetail.jsp").forward(req, resp);
    }
}

