<%--
  Created by IntelliJ IDEA.
  User: devjenny
  Date: 2023-06-14
  Time: 오후 4:23
  To change this template use File | Settings | File Templates.

  * 해야할 거
  - xss 처리 (script로 접근 못 하게)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>게시물 등록</title>
    <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.1.9.2.min.js' />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">



</head>
<body>

<div class="container">
    <h2 class="my-3 border-bottom pb-2">글쓰기</h2>
    <form name="regForm" id="regForm" method="post" action="/guide/page/noticeResister">


        <div class="mb-3">
            <label for="b_writer" class="form-label">작성자</label>
            <input type="text" name="b_writer" id="b_writer" class="form-control">
        </div>
        <div class="mb-3">
            <label for="b_password" class="form-label">비밀번호</label>
            <input type="password" name="b_password" id="b_password" class="form-control">
        </div>
        <div class="mb-3">
            <label for="b_title" class="form-label">제목</label>
            <input type="text" name="b_title" id="b_title" class="form-control" >
        </div>
        <div class="mb-3">
            <label for="b_contents" class="form-label">내용</label>
            <textarea type="text" name="b_contents" id="b_contents" class="form-control" rows="10" ></textarea>
        </div>

        <input type="button" value="확인" class="btn btn-success" onclick="fnResBtn()">
        <input type="button" value="취소" class="btn btn-danger" onclick="fnCancle()">
    </form>
</div>

<script>

    function fnResBtn() {

        // 작성자, 제목, 내용 미입력 시 경고창
        if ($('#b_writer').val() == "") {
            alert("작성자를 입력해주십시오.");
            $('#b_writer').focus();
            return;
        }
        if ($('#b_password').val() == "") {
            alert("비밀번호를 입력해주세요.");
            $('#b_password').focus();
            return;
        }
        if ($('#b_title').val() == "") {
            alert("제목을 입력해주십시오.");
            $('#b_title').focus();
            return;
        }
        if ($('#b_contents').val() == "") {
            alert("내용을 입력해주십시오.");
            $('#b_contents').focus();
            return;
        }

        alert("성공");
        $('#regForm').submit();
    }

    function fnCancle() {
        location.href = "/guide/page/noticeList";
    }
</script>


</body>
</html>
