<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String id = "";
	boolean checked = false;
	Cookie[] cookieArr = request.getCookies();
	if (cookieArr != null) {
		for (Cookie cookie : cookieArr) {
			if ("id".equals(cookie.getName())) {

				id = cookie.getValue();
				checked = true;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cookie Login</title>

	<style>
		body {
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100vh;
			margin: 0;
			font-family: Arial, sans-serif;
			background-color: white;
		}

		.login-box {
			width: 300px;
			padding: 20px;
			margin: 0 auto;
			border: 1px solid skyblue;
			border-radius: 5px;
			background-color: skyblue;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}

		.input-group {
			margin-bottom: 10px;
		}

		.input-group label {
			display: inline-block;
			width: 80px;
		}

		.input-group input[type="text"], .input-group input[type="password"] {
			width: 100%;
			padding: 8px;
			box-sizing: border-box;
		}

		.remember-me {
			margin-top: 10px;
		}

		.submit-btn {
			margin-top: 20px;
			width: 100%;
			padding: 10px;
			background-color: skyblue;
			color: white;
			border: none;
			border-radius: 5px;
			cursor: pointer;
		}

		.submit-btn:hover {
			background-color: blue;
		}
	</style>
</head>
<body>
<div class="login-box">
	<form action="<%=request.getContextPath()%>/cookieLoginServlet.do"
		  method="post">
		<div class="input-group">
			<label for="id">ID :</label> <input type="text" id="id" name="id"
												placeholder="ID 입력하세요." value="<%=id%>">
		</div>
		<div class="input-group">
			<label for="pass">PASS :</label> <input type="password" id="pass"
													name="pass" placeholder="PassWord 입력하세요.">
		</div>
		<div class="remember-me">
			<input id="check" type="checkbox" name="check" value="check"
				<%=checked ? "checked" : ""%>> <label for="check">ID
			기억하기</label>
		</div>
		<input type="submit" value="Login" class="submit-btn">
	</form>
</div>
</body>
</html>