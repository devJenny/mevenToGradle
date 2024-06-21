<%--
  Created by IntelliJ IDEA.
  User: devjenny
  Date: 2023-06-28
  Time: 오후 4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<html>
<head>
    <title>회원가입</title>
  <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
  <script type="text/javascript" src="<c:url value='/js/jquery-ui.1.9.2.min.js' />"></script>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css' />">
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">

</head>
<body>

<div class="container w-50">
      <form name="joinForm" id="joinForm" method="post" action="/guide/join">
        <h2 class="my-3 border-bottom pb-2">회원가입</h2>
        <div class="form-group">
          <input type="text" class="form-control my-3" placeholder="아이디" name="userID" maxlength="20" required>
        </div>
        <div class="form-group">
          <input type="password" class="form-control my-3" placeholder="비밀번호" id="userPassword" name="userPassword" maxlength="20" required>
        </div>
        <div class="form-group">
          <input type="text" class="form-control my-3" placeholder="이름" name="userName" maxlength="20" required>
        </div>
        <div class="form-group" style="text-align: center;">
          <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-primary my-3 active">
              <input type="radio" name="userGender" autocomplete="off" value="남자" checked required>남자
            </label>
            <label class="btn btn-primary my-3">
              <input type="radio" name="userGender" autocomplete="off" value="여자" checked>여자
            </label>
          </div>
        </div>
        <div class="form-group">
          <input type="email" class="form-control my-3" placeholder="이메일" name="userEmail" maxlength="20" required>
        </div>
<%--        <input type="button" onclick="fnJoinSubmitBtn()" class="btn btn-primary form-control" value="회원가입">--%>
        <input type="submit" onclick="return confirm('회원가입을 하시겠습니까?')" class="btn btn-primary form-control" value="회원가입">
      </form>
</div>

<script>



</script>
</body>
</html>