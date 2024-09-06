package kr.or.ddit.memberList.controller;

import kr.or.ddit.json.LprodServiceImpl;
import kr.or.ddit.memberList.dao.IMemberService;
import kr.or.ddit.memberList.dao.MemberServiceImpl;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/memberList.do")
public class MemberList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        //응답 데이터를 JSON으로 할때 설정
        resp.setCharacterEncoding("UTF-8");

        IMemberService service = MemberServiceImpl.getInstance();

        List<MemberVO> memberList = service.selectAllMember();

        req.setAttribute("memberList", memberList);

        req.getRequestDispatcher("/basic/memberList/memberList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
