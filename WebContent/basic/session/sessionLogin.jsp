<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

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
    <%
        //세션 로그인 값 - 로그인컨트롤러에서 갖고옴
        //여기에서 sessionLogin.java에서 로그인 성공여부를 갖고와야함
        String inputId = "";
        String loginMessage = (String) session.getAttribute("loginMessage");
        String id = (String) session.getAttribute("id");
        inputId = (String) session.getAttribute("inputId"); // 실패 시 입력된 ID를 세션에서 가져옴

        if (id != null) {
    %>
    <h2><%= id %>님 반갑습니다!</h2>
    <a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃하기</a><br><br>
    <%
        } else {
            if (loginMessage != null) {
    %>
    <p style="color:red;"><%= loginMessage %></p>
    <%
            }
    %>
      <form action="<%=request.getContextPath()%>/sessionLogin.do" method="post">
        <div class="input-group">
            <label for="id">ID :</label>
            <input type="text" id="id" name="id" placeholder="ID를 입력하세요."  value="<%=(inputId != null) ? inputId : ""%>">
        </div>
        <div class="input-group">
            <label for="pass">PASS :</label>
            <input type="password" id="pass" name="pass" placeholder="PassWord를 입력하세요.">
        </div>
        <input type="submit" value="Login" class="submit-btn">
    </form>
    <%
        }  // 이 부분에서 else 블록을 닫습니다.
    %>
</div>
</body>
</html>