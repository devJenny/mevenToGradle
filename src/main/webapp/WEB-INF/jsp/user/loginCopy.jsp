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
    <form name="loginForm" id="loginForm" method="post" action="/guide/login">
        <h2 class="my-3 border-bottom pb-2">로그인</h2>
        <div class="form-group">
            <input type="text" class="form-control my-3" id="userID" placeholder="아이디" name="userID" maxlength="20"
                   required>
        </div>
        <div class="form-group">
            <input type="password" class="form-control my-3" id="userPassword" placeholder="비밀번호" name="userPassword"
                   maxlength="20" required>
        </div>

        <input type="button" onclick="fnLoginBtn()" class="btn btn-primary form-control" value="로그인">
    </form>
</div>

<div id="temps"></div>
<script>


    function fnLoginBtn() {
        <c:set value="${inputId}" var="inputId"/>
        <c:set value="${inputPassword}" var="inputPassword"/>

        let userId = "${inputId}"
        let userPassword = "${inputPassword}"
        console.log(userId);
        console.log(userPassword)


        <c:forEach items="${customerList}" var="item">

        let customerId = "${item.userID}";
        let customerpw = "${item.userPassword}";

        if (userId == customerId) {
            if (userPassword == customerpw) {
                prompt("로그인 하시겠습니까?")
            } else {
                alert("비밀번호가 일치하지 않습니다.");
                return;
            }
        } else {
            alert("아이디를 확인해주세요.");
            return;
        }
        </c:forEach>
    }

</script>
</body>
</html>
