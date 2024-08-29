<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <%

        String name = "홍길동";
    %>


</head>
<body>
<h2><%=name %>의 Request연습 Form(숫자 입력은 정수형으로 입력하세요.)

    <form action="<%=request.getContextPath() %>/RequestTest02.do" method="post">
        <tr>
            <td><input type="text" size="10" name="num1"> </td>
        </tr>
        <tr>
            <td>
                <select name="sign">
                    <option value="+">+</option>
                    <option value="-">-</option>
                    <option value="*">*</option>
                    <option value="/">/</option>
                    <option value="%">%</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="text" size="10" name="num2"> </td>
        </tr>
        <tr>
            <td colspan="1" style="text-align: center">
                <input type="submit" value="확인">
            </td>
        </tr>
    
    </form>
</body>
</html>