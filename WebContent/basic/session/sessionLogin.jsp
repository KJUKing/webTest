<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionLogin</title>
</head>
<body>
<%
	// JSP문서에서의 Session은 'session'이라는 이름으로 이미 저장되어 있다
	String id = (String)session.getAttribute("id");
	if(id == null){
%>
		<form action="<%=request.getContextPath()%>/sessionLogin.do" method="post">
			<table border="1">
				<tr>
					<td>ID :</td>
					<td><input type="text" name="id" placeholder="ID를 입력하세요"></td>
				</tr>
				<tr>
					<td>PASS :</td>
					<td><input type="password" name="pass" placeholder="PASSWORD를 입력하세요"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
				</tr>
			</table>
		</form>
<%		
	} else {
%>
		<h3><%=id%>님 반갑습니다.</h3><br>
		<a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>
<%
	}
%>
</body>
</html>