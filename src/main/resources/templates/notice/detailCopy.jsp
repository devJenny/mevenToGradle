<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: devjenny
  Date: 2023-06-14
  Time: 오후 5:09
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.1.9.2.min.js' />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">

    <style>
        input[type="text"]:disabled, textarea[type="text"]:disabled {
            background: #F0F0F0;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="my-3 border-bottom pb-2">게시물</h2>

    <form id="listForm" name="listForm" action="post">
        <c:forEach var="board" items="${board}" varStatus="status">

            <div class="mb-3">
                <label for="b_no" class="form-label">번호</label>
                <input type="text" name="b_no" id="b_no" value="${board.b_no}" class="form-control" disabled>
            </div>
            <div class="mb-3">
                <label for="b_hits" class="form-label">조회수</label>
                <input type="text" name="b_hits" id="b_hits" class="form-control" value="${board.b_hits}"
                       disabled>
            </div>
            <div class="mb-3">
                <label for="b_writer" class="form-label">작성자</label>
                <input type="text" name="b_writer" id="b_writer" class="form-control" value="${board.b_writer}"
                       disabled>
            </div>
            <div class="mb-3">
                <label for="b_title" class="form-label">제목</label>
                <input type="text" name="b_title" id="b_title" value="${board.b_title}" class="form-control" disabled>
            </div>
            <div class="mb-3">
                <label for="b_contents" class="form-label">내용</label>
                <textarea type="text" name="b_contents" id="b_contents" class="form-control" rows="10"
                          disabled>${board.b_contents}</textarea>
            </div>

            <%-- 버튼 --%>
            <a href="/guide/page/noticeList" class="btn btn-success">목록</a>
            <a href="/guide/page/update/${board.b_no}" class="btn btn-primary">수정</a>
            <input type="button" onclick="fnDeleteBtn()" class="btn btn-danger" value="삭제">

        </c:forEach>
    </form>
</div>

<%-- 댓글 영역 --%>
<div class="container">
    <br/>
    <h3 class="my-3 border-bottom pb-2">댓글 작업중 ❗❗❗</h3>

    <form method="post" id="commentForm">
        <c:forEach var="board" items="${board}" varStatus="status">
            <div class="container">
                <div class="mb-3 row">
                    <div class="col-auto">
                        <label for="c_writer" class="col-form-label">댓글 작성자</label>
                    </div>
                    <div class="col-auto">
                        <input type="text" name="c_writer" id="c_writer" maxlength="10" class="form-control"
                               placeholder="10자 이내">
                    </div>
                    <div class="col-auto">
                        <label for="c_password" class="col-form-label">비번 입력</label>
                    </div>
                    <div class="col-auto">
                        <input type="text" name="c_password" id="c_password" maxlength="10" class="form-control"
                               placeholder="10자 이내">
                    </div>
                </div>


                <div class="mb-3 row">
                    <textarea rows="1" cols="90" name="c_contents" id="c_contents" placeholder="50자 이내로 댓글을 작성해주세요."
                              maxlength="50" class="form-control"></textarea>
                </div>
                <p>
                    <input tpye="button" onclick="fnWriteComment()" class="btn btn-outline-primary" value="댓글 작성"/>
                        <%--                <input tpye="button" id="regComment" class="btn btn-outline-primary" value="댓글 작성"/>--%>
                </p>
                <p>
                    <input type="hidden" name="b_no" value="${board.b_no}">
                </p>
            </div>
        </c:forEach>
    </form>
</div>

<br/>
<%--<div class="container">--%>
<%--    <h5 class="my-3 border-bottom pb-2">댓글 리스트</h5>--%>
<%--    <div>댓글 목록</div>--%>
<%--</div>--%>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">댓글 리스트</h5>
    <ul>
        <c:forEach items="${comment}" var="comment">

            <div>
                <p> 👉 ${comment.c_writer} / <fmt:formatDate value="${comment.reg_dt}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/> / <a
                        href="/guide/page/noticeList" class="btn btn-outline-success btn-sm">수정</a>
                    <a href="/guide/page/noticeList" class="btn btn-outline-danger btn-sm">삭제</a></p>

                <div class="mb-3">
                        <textarea type="text" class="form-control" rows="1"
                                  disabled>${comment.c_contents}</textarea>
                </div>
            </div>
        </c:forEach>
    </ul>
</div>


<script>
    function fnDeleteBtn() {
        // alert("삭제하시겠습니까?");
        <c:forEach var="board" items="${board}" varStatus="status">
        const confirmPass = ${board.b_password};
        const password = prompt("삭제하시려면 비밀번호를 입력해주세요.");
        if (confirmPass == password) {
            alert("게시물을 삭제하겠습니다.")
            location.href = "/guide/page/delete/${board.b_no}"
        } else {
            alert("비밀번호가 일치하지 않습니다.")
        }
        </c:forEach>
    }

    function fnWriteComment() {
        if ($('#c_writer').val() == "") {
            alert("작성자를 입력해주세요.");
            $('#c_writer').focus();
            return
        }
        if ($('#c_password').val() == "") {
            alert("비밀번호를 입력해주세요.");
            $('#c_password').focus();
            return
        }
        if ($('#c_contents').val() == "") {
            alert("내용을 입력해주세요.");
            $('#c_contents').focus();
            return
        }

        alert("댓글을 작성하겠습니까?");
        $('#commentForm').attr("action", "/guide/comment/write").submit();
    }


</script>
</body>
</html>
