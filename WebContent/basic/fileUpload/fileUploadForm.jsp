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
<h3>File Upload 연습</h3>
<form method="post" enctype="multipart/form-data"
        action="<%=request.getContextPath()%>/fileUpload.do">

    작성자 이름 : <input type="text" name="userName"><br><br>
    한개 파일 선택 : <input type="file" name="upFile1"><br><br>
    여러개 파일 선택 : <input type="file" name="upFile2" multiple><br><br>
    <input type="submit" value="전송">
</form>

</body>

</html>