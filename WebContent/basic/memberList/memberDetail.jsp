<%@ page import="kr.or.ddit.vo.MemberVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request 객체에서 member 정보를 가져온다.
    MemberVO member = (MemberVO) request.getAttribute("member");
%>

<html>
<head>
    <title>회원 정보 상세보기</title>
    <style>
        .container {
            width: 300px;
            border: 1px solid blue;
            padding: 10px;
            margin: 0 auto;
            text-align: center;
        }
        .container img {
            width: 100px;
            height: 100px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid lightgray;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        .button-group input {
            margin: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>회원 정보 상세보기</h2>
    <!-- 회원 사진 -->
    <img src="<%=request.getContextPath()%>/imageView.do?filepath=<%=member.getMem_photo()%>" alt="회원 사진" />



    <!-- 회원 정보 테이블 -->
    <table>
        <tr>
            <th>회원ID</th>
            <td><%=member.getMem_id()%></td>
        </tr>
        <tr>
            <th>비밀번호</th>
            <td><%=member.getMem_pass()%></td>
        </tr>
        <tr>
            <th>회원이름</th>
            <td><%=member.getMem_name()%></td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td><%=member.getMem_tel()%></td>
        </tr>
        <tr>
            <th>회원주소</th>
            <td><%=member.getMem_addr()%></td>
        </tr>
    </table>

    <!-- 버튼들 -->
    <div class="button-group">
        <input type="button" value="수정" onclick="location.href='<%=request.getContextPath()%>/updateMemberForm.do?id=<%=member.getMem_id()%>'">
        <input type="button" value="삭제" onclick="location.href='<%=request.getContextPath()%>/deleteMember.do?id=<%=member.getMem_id()%>'">
        <input type="button" value="회원목록" onclick="location.href='<%=request.getContextPath()%>/memberList.do'">
    </div>
</div>

</body>
</html>
