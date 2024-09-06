package kr.or.ddit.memberList.controller;

import kr.or.ddit.memberList.dao.IMemberService;
import kr.or.ddit.memberList.dao.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/idCheck.do")
public class IdCheck extends HttpServlet {

    public IdCheck() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전송 데이터 받기 - id
        String id = req.getParameter("id");

        //service객체 얻기
        IMemberService service = MemberServiceImpl.getInstance();

        //service메소드 호출 - 결과값 얻기
        String resId = service.idCheck(id);

        //request에 저장
        req.setAttribute("resultId", resId);

        //view페이지로 이동
        req.getRequestDispatcher("/basic/memberList/idCheck.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}