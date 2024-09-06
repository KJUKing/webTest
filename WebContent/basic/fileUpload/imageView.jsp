<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.vo.LprodVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h2>이미지 처리하기</h2>
<img src="<%=request.getContextPath()%>/images/수지.gif"/><br><br>
<img src="<%=request.getContextPath()%>/images/imageView.do?fileno=3"><br><br>
<a href="<%=request.getContextPath()%>/fileList.do">갖고오기</a>


<div id="result"></div>

</body>

</html>
