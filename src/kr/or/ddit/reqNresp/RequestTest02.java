package kr.or.ddit.reqNresp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RequestTest02.do")
public class RequestTest02 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST방식으로 전달되는 데이터의 문자 인코딩 방식
        request.setCharacterEncoding("utf-8");

        String num1 = request.getParameter("num1");
        String sign = request.getParameter("sign");
        String num2 = request.getParameter("num2");

        Double numInt = Double.parseDouble(num1);
        Double numInt2 = Double.parseDouble(num2);

        double result = 0;
        boolean calcOk = true;


        switch (sign) {
            case "+":
                result = numInt + numInt2;
                break;
            case "-":
                result = numInt - numInt2;
                break;
            case "*":
                result = numInt * numInt2;
                break;
            case "/":
                if (numInt2 != 0) {
                    result = numInt / numInt2;
                } else {
                    calcOk = false;
                }
                break;
            case "%":
                if (numInt2 != 0) {
                    result = numInt % numInt2;
                } else {
                    calcOk = false;
                }
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><meta charset=\"utf-8\"><title>Request객체연습</title></head>");
        out.println("<body>");
        out.println("<h2> 계산 결과 </h2>");

        if (calcOk == true) {
            out.print(num1 + "  ");
            out.println(sign + "  ");
            out.println(num2 + "  ");
            out.println(" = ");
            out.println(result);
        } else {
            out.print("계산 불능(0으로 나눔)<br>");
        }

        out.println("<hr>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }
}
