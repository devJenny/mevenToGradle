<%--
  Created by IntelliJ IDEA.
  User: devjenny
  Date: 2023-06-28
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>
<html>
<head>
    <title>Title</title>
  <%-- jQuery --%>
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
<%-- 내비게이션 바--%>

<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<c:url value="/images/1.jpg"/> " class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>HOME</h5>
        <p>웰컴 웰컴 홈</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="<c:url value="/images/2.jpg"/>" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5></h5>
        <p>웰컴 웰컴 홈</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="<c:url value="/images/3.jpg"/>" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5></h5>
        <p>웰컴 웰컴 홈</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<%--<div class="container mt-3">--%>
<%--  <div class="mt-4 p-5 bg-Danger text-white rounded">--%>
<%--    <h1>JE HOME</h1>--%>
<%--    <p>Welcome to my Home :)</p>--%>
<%--    <p><a href="/guide/page/noticeList" style="color:white; text-decoration: none;">게시판 이동</a></p>--%>
<%--  </div>--%>
<%--</div>--%>

</body>
</html>
