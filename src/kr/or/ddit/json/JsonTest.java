package kr.or.ddit.json;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/json/jsonTest.do")
public class JsonTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        //응답 데이터를 JSON으로 할때 설정
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        //파라미터 데이터 받기
        String choice = req.getParameter("choice");

        Gson gson = new Gson();
        //처리한 결과를 JSON현태의 문자열로 변환한 후 문자열을 응답으로 보낸다
        String jsonData = null;
        switch (choice) {
            case "String" :
                String str = "안녕하세요"; //처리한 결과
                jsonData = gson.toJson(str);
                break;
            case "array" :
                int[] intArr = {1, 2, 3};
                jsonData = gson.toJson(intArr);
                break;
            case "object" :
                SampleVO sampleVO = new SampleVO(1, "홍길동");
                jsonData = gson.toJson(sampleVO);
                break;
            case "list":
                List<SampleVO> samList = new ArrayList<SampleVO>();
                samList.add(new SampleVO(100, "이순신"));
                samList.add(new SampleVO(200, "핫식스"));
                samList.add(new SampleVO(300, "오뚜기"));
                jsonData = gson.toJson(samList);
                break;
            case "map":
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "이몽룡");
                map.put("age", "18");
                map.put("addr", "대전");
                jsonData = gson.toJson(map);
                break;
        }

        //Json문자열 확인용
        System.out.println("jsonData = " + jsonData);

        resp.getWriter().write(jsonData);
        resp.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
