<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.vo.LprodVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <%
        List<LprodVO> lprodList = (List<LprodVO>) request.getAttribute("lprodList");
    %>
</head>
<body>
<h3>
    Lprod 자료 목록 (동기방식)
</h3>
<table border="1">
    <tr>
        <th>LPROD_ID</th>
        <th>LPROD_GU</th>
        <th>LPROD_NM</th>
    </tr>
    <%
        for (LprodVO lvo : lprodList) {
    %>
    <tr>
        <td><%=lvo.getLprod_id()%>
        </td>
    </tr>
    <tr>
        <td><%=lvo.getLprod_gu()%>
        </td>
    </tr>
    <tr>
        <td><%=lvo.getLprod_nm()%>
        </td>
    </tr>
    <%
        }
    %>
</table>

<h3>Lprod 자료 목록</h3>
<div id="result"></div>

</body>

</html>