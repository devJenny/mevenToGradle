<%--
  Created by IntelliJ IDEA.
  User: dvejenny
  Date: 2023-06-28
  Time: 오후 5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
  <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js' />"></script>
  <script type="text/javascript" src="<c:url value='/js/bootstrap.esm.min.js' />"></script>
  <script type="text/javascript" src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
  <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
  <script type="text/javascript" src="<c:url value='/js/jquery-ui.1.9.2.min.js' />"></script>

  <%-- bootstrap --%>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css' />">
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
</head>
<body>
<%-- 내비게이션 바 시작--%>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">💛HOME💛</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/guide/">홈</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/guide/page/noticeList">게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/guide/join">회원가입</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/guide/login">로그인</a>
        </li>
        <%--                <li class="nav-item dropdown">--%>
        <%--                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"  aria-expanded="false">--%>
        <%--                        접속--%>
        <%--                    </a>--%>
        <%--                    <ul class="dropdown-menu">--%>
        <%--                        <li><a class="dropdown-item" href="#">Action</a></li>--%>
        <%--                        <li><a class="dropdown-item" href="#">Another action</a></li>--%>
        <%--                        <li><a class="dropdown-item" href="#">Something else here</a></li>--%>
        <%--                    </ul>--%>
        <%--                </li>--%>
      </ul>
    </div>
  </div>
</nav>
<%-- 내비게이션 바 종료--%>
</body>
</html>
