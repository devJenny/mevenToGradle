<%--
  Created by IntelliJ IDEA.
  User: devjenny
  Date: 2023-06-16
  Time: 오후 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.1.9.2.min.js' />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">

</head>
<body>
<div class="container">
    <h2 class="my-3 border-bottom pb-2">업데이트</h2>

    <form id="listForm" name="listForm" method="post">
        <c:forEach var="board" items="${updateBoard}" varStatus="status">


            <div class="mb-3">
                <label for="b_no" class="form-label">번호</label>
                <input type="text" name="b_no" id="b_no" value="${board.b_no}" class="form-control" disabled>
                <input type="hidden" name="b_no" value="${board.b_no}">
            </div>
            <div class="mb-3">
                <label for="b_writer" class="form-label">작성자</label>
                <input type="text" name="b_writer" id="b_writer" class="form-control" value="${board.b_writer}"
                       disabled>
                <input type="hidden" name="b_writer" value="${board.b_writer}">
            </div>
            <div class="mb-3">
                <label for="b_password" class="form-label">비밀번호 입력</label>
                <input type="password" name="b_password" id="b_password"  class="form-control">

            </div>
            <div class="mb-3">
                <label for="b_title" class="form-label">제목</label>
                <input type="text" name="b_title" id="b_title" value="${board.b_title}" class="form-control">
            </div>
            <div class="mb-3">
                <label for="b_contents" class="form-label">내용</label>
                <textarea type="text" name="b_contents" id="b_contents" class="form-control"
                          rows="10">${board.b_contents}</textarea>
            </div>


        </c:forEach>
        <%-- 버튼 --%>
        <input type="button" class="btn btn-primary" value="수정" onclick="fnUpdateBtn()">
        <a href="/guide/page/noticeList" class="btn btn-danger">취소</a>
    </form>
</div>


<script>
    function fnUpdateBtn() {

        <c:forEach var="board" items="${updateBoard}" varStatus="status">
        const confirmPass = ${board.b_password}
        const inputPass = document.querySelector("#b_password").value;

        if (confirmPass == inputPass) {
            confirm("수정하시겠습니까?");
            $('#listForm').attr("action", "<c:url value='/page/update'/>").submit();
        } else {
            alert("비밀번호가 일치하지 않습니다!")
        }
        </c:forEach>

    }
</script>
</body>
</html>