package kr.or.ddit.json;


import com.google.gson.Gson;

import kr.or.ddit.vo.LprodVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/json/lprodList.do")
public class LprodList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        //응답 데이터를 JSON으로 할때 설정
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        Gson gson = new Gson();
        String jsonData = null;

        LprodServiceImpl service = LprodServiceImpl.getInstance();
        List<LprodVO> lprodVOList = service.selectLprod();

//        Map<Object, Object> map = new HashMap<Object, Object>();
//        for (LprodVO vo : lprodVO) {
//            map.put("id", vo.getLprod_id());
//            map.put("gu", vo.getLprod_gu());
//            map.put("nm", vo.getLprod_nm());
//        }
        jsonData = gson.toJson(lprodVOList);

        //Json문자열 확인용
        System.out.println("jsonData = " + jsonData);

        resp.getWriter().write(jsonData);
        resp.flushBuffer();
    }
}
