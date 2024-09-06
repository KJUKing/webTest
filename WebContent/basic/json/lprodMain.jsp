<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>


    <script type="text/javascript">
        $(function () {
            $('#lprodBtn').on("click", function () {
                $.ajax({
                    url: "<%=request.getContextPath()%>/json/lprodList.do", //요청을 보낼 주소
                    type: "POST", //전송방식 (GET, POST)
                    data: {}, // 서버로 전송할 데이터 구성, 여기서는 빈 객체를 보냅니다

                    success: function (data) { // 응답 정상처리

                        let htmlCode = "<h3>Lprod 자료 목록</h3>";

                        htmlCode += "<table border='1'>";
                        htmlCode += "<tr><th>LPROD_ID</th><th>LPROD_GU</th><th>LPROD_NM</th></tr>"; // 테이블 헤더 추가

                        $.each(data, function(index, item) {
                            htmlCode += "<tr>";
                            htmlCode += "<td>" + item.lprod_id + "</td>";
                            htmlCode += "<td>" + item.lprod_gu + "</td>";
                            htmlCode += "<td>" + item.lprod_nm + "</td>";
                            htmlCode += "</tr>";
                        });
                        htmlCode += "</table>";
                        $("#result").html(htmlCode);
                    },
                    error: function (xhr) { // 오류 응답 처리
                        alert("응답 코드 : " + xhr.status);
                    },
                    dataType: "json" // 응답으로 받을 데이터의 타입
                });
            });
            $('#lprodBtn2').on('click', function () {
                location.href = "<%=request.getContextPath()%>/json/lprodList2.do";
            });
        });


    </script>
</head>
<body>
<input type="button" id="lprodBtn" value="Lprod자료 가져오기">
<input type="button" id="lprodBtn2" value="Lprod자료 가져오기2">
<h3>Lprod 자료 목록</h3>
<div id="result"></div>

</body>

</html>