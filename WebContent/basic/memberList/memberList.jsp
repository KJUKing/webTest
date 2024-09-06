<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.vo.LprodVO" %>
<%@ page import="kr.or.ddit.vo.MemberVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <script src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.serializejson.min.js"></script>


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


    <script>

        $(function () {
            //id중복검사
            $('#idcheck').on('click', function () {
                //입력한 id값을 가져온다
                userId = $('#id').val().trim();

                if (userId.length < 1) {
                    alert("id입력하세요");
                    return;
                }
                $.ajax({
                    url: '<%=request.getContextPath()%>/idCheck.do',
                    type: 'get',
                    data: {id: userId},  //data : "id=" + userId,
                    contentType: 'application/json',
                    success: function (res) {
                        $('#idspan').html(res.flag).css('color', 'red');
                    },
                    error: function (xhr) {
                        alert("오류 " + xhr.status)
                    },
                    dataType: 'json'
                })

            })//id중복검사

            // 비밀번호 확인 입력란에 실시간 이벤트 등록
            document.getElementById('mem_pass_2').addEventListener('keyup', function () {
                // 입력된 비밀번호와 비밀번호 확인 필드 값을 가져옴
                var pass = document.getElementById('mem_pass').value;
                var pass2 = document.getElementById('mem_pass_2').value;

                // 비교 후 메시지를 표시함
                if (pass !== pass2) {
                    document.getElementById('pass_check_message').textContent = "비밀번호가 일치하지 않습니다.";
                } else {
                    document.getElementById('pass_check_message').textContent = "";  // 비밀번호가 일치하면 메시지를 지움
                }
            });


        })//$function
    </script>
    <%
        List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");
    %>

</head>
<body>

<h3>회원 목록 보기</h3>
<h3>
    회원 리스트
</h3>
<table border="1">
    <input class="btn btn-outline-info btn-sm" type="button" value="회원추가" data-bs-toggle="modal"
           data-bs-target="#myModal" id="insertMember">
    <tr>
        <th>ID</th>
        <th>비밀번호</th>
        <th>이 름</th>
        <th>전 화</th>
        <th>주 소</th>
    </tr>
    <%
        for (MemberVO mvo : memberList) {
    %>
    <tr>
        <td><a href="<%=request.getContextPath()%>/memberDetail.do?id=<%=mvo.getMem_id()%>">
            <%=mvo.getMem_id()%>
        </a>
        </td>
        <td><%=mvo.getMem_pass()%>
        </td>
        <td><%=mvo.getMem_name()%>
        </td>
        <td><%=mvo.getMem_tel()%>
        </td>
        <td><%=mvo.getMem_addr()%>
        </td>
    </tr>
    <%
        }
    %>
</table>

<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">회원 정보 입력 폼</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <form action="<%=request.getContextPath()%>/insertMember.do" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    회원 ID
                    <input class="btn btn-outline-info btn-sm" type="button" value="중복검사" id="idcheck">
                    <span id="idspan"></span>
                    <input type="text" id="id" name="mem_id" placeholder="Enter id">
                </div>

                <div class="modal-body">
                    비밀번호
                    <input type="password" id="mem_pass" name="mem_pass" placeholder="비밀번호">
                </div>

                <div class="modal-body">
                    비밀번호 확인
                    <input type="password" id="mem_pass_2" placeholder="비밀번호 확인">
                    <span id="pass_check_message" style="color:red;"></span>
                </div>

                <div class="modal-body">
                    회원 이름
                    <input type="text" id="mem_name" name="mem_name" placeholder="회원 이름">
                </div>

                <div class="modal-body">
                    전화번호
                    <input type="text" id="mem_tel" name="mem_tel" placeholder="전화번호">
                </div>

                <div class="modal-body">
                    주소
                    <input type="text" id="mem_addr" name="mem_addr" placeholder="주소">
                </div>

                <div class="modal-body">
                    프로필 사진
                    <input type="file" id="mem_photo" name="mem_photo" placeholder="프로필 사진"><br><br>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <input type="submit" value="저장" id="insertBtn" class="btn btn-primary">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
                </div>
            </form>

        </div>
    </div>
</div>


</body>
</html>



