package kr.or.ddit.json;

import com.google.gson.Gson;
import kr.or.ddit.vo.LprodVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/json/lprodList2.do")
public class LprodList2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        //응답 데이터를 JSON으로 할때 설정
        resp.setCharacterEncoding("UTF-8");

        LprodServiceImpl service = LprodServiceImpl.getInstance();
        List<LprodVO> lprodVOList = service.selectLprod();

        req.setAttribute("lprodList", lprodVOList);

        req.getRequestDispatcher("/basic/json/lprodList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
