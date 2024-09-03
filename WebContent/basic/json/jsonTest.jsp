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
            $('#mapBtn').on("click", function () {


                $.ajax({
                    url: "<%=request.getContextPath()%>/json/jsonTest.do",//요청을 보낼 주소
                    type: "POST", //전송방식 (GET, POST)
                    data: "choice=map", //서버로 전송할 데이터 구성

                    //------------위3개는 전송용 아래3개는 응답용--------
                    success: function (data) { // 응답 정상처리
                        // 매개변수 data에는 응답으로 온 데이터가 자동으로 저장된다.
                        // let htmlCode = "<h3>문자열 응답 결과</h3>";
                        // data = [10,20,30,40,50];
                        //  let htmlCode = "<h3> 배열 응답 결과</h3>";
                        //  $.each(data, function (i,v){
                        //      htmlCode += i + "번째 자료 : " + v + "<br>";
                        //  });
                        // data = {"num" :1, "name":"홍길동"} 이런형식으로옴
                        //   let htmlCode = "<h3> 배열 응답 결과</h3>";
                        // htmlCode += "번호 : " +data.num + "<br>";
                        // htmlCode += "이름 : " +data.name + "<br>";
                        // dta = [{"num" :100, "name" :"이순신"}, {"num" :200, "name" :"핫식스"}, ~~~ ]


                        // let htmlCode = "<h3> 리스트 응답 결과</h3>";
                        //for (let i = 0; i < data.length; i++) {
                        //    htmlCode += i + "번째자료<br>";
                        //    htmlCode += "번호 : " + data[i].num + "<br>";
                        //    htmlCode += "이름 : " + data[i].name + "<hr>";
                        //}
                        //  $.each(data, function (i, v) {
                        //      htmlCode += i + "번째자료<br>";
                        //      htmlCode += "번호 : " + v.num + "<br>";
                        //      htmlCode += "이름 : " + v.name + "<hr>";
                        //  });

                        let htmlCode = "<h3> 맵 응답 결과</h3>";
                        htmlCode += "이름 : " + data.name + "<br>";
                        htmlCode += "나이 : " + data.age + "<br>";
                        htmlCode += "주소 : " + data.addr + "<br>";


                        $("#result").html(htmlCode);
                    },
                    error: function (xhr) { //오류 응답 처리
                        alert("응답 코드 : " + xhr.status);
                    },
                    dataType: "json" //응답으로 받을 데이터의 타입
                });
            });
        });

    </script>
</head>
<body>
<input type="button" id="strBtn" value="문자열">
<input type="button" id="arrayBtn" value="배 열">
<input type="button" id="objBtn" value="객 체">
<input type="button" id="listBtn" value="리스트">
<input type="button" id="mapBtn" value="Map">
</body>

<div id="result"></div>

</html>