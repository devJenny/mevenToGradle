<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>
<!DOCTYPE html>
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

            <%--            <div class="mb-3">--%>
            <%--                <label for="b_no" class="form-label">번호</label>--%>
            <%--                <input type="text" name="b_no" id="b_no" value="${board.b_no}" class="form-control" disabled>--%>
            <%--            </div>--%>
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

    <div class="container comment-box">

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
                <input type="password" name="c_password" id="c_password" maxlength="10" class="form-control"
                       placeholder="10자 이내">
            </div>
        </div>
        <div class="mb-3 row">
                    <textarea rows="1" cols="90" name="c_contents" id="c_contents" placeholder="50자 이내로 댓글을 작성해주세요."
                              maxlength="50" class="form-control"></textarea>
        </div>
        <input type="hidden" id="reg_dt" name="reg_dt">
        <p>
            <button id="regComment" class="btn btn-outline-primary">댓글 작성</button>
        </p>
    </div>
</div>

<br/>
<div class="container">
    <h5 class="my-3 border-bottom pb-2" id="commentBox">댓글 리스트</h5>
    <%--    <div class="comment-count"> 댓글 <span id="count">0</span></div>--%>
    <div class="commentList">댓글 목록</div>
</div>


<script>
    <%--  게시물 삭제  --%>

    function fnDeleteBtn() {

        // 초반에 기능을 구현했을 때는 비밀번호 입력 시, 숫자만 인식하고 문자로 입력했을 시에는 프롬프트의 '확인' 버튼이 작동을 안 함
        // 백에서 model.addAttribute로 넘긴 패스워드를 const에 담았는데! 이때 "" <- 큰 따옴표가 빠져서 그랬던 거임
        // db에서 pw를 문자열 처리했기 때문에, 넘겨 받을 때도 문자열 처리를 한 후, 사용자가 입력한 값이랑 비교를 해야함

        <c:forEach var="board" items="${board}" varStatus="status">
        const password = prompt("삭제하시려면 비밀번호를 입력해주세요.");
        const confirmPass = "${board.b_password}";
        if (confirmPass == password) {

            confirm("게시물을 삭제하시겠습니까?.")
            location.href = "/guide/page/delete/${board.b_no}"
        } else {
            alert("비밀번호가 일치하지 않습니다.")
        }
        </c:forEach>
    }




    // 댓글 입력
    $('#regComment').click(function () {
        <%--        <c:forEach items="${comment}" var="comment">--%>
        <c:forEach var="board" items="${board}" varStatus="status">
        const b_no = ${board.b_no};
        </c:forEach>

        const c_writer = $('#c_writer').val();
        const c_password = $('#c_password').val();
        const c_contents = $('#c_contents').val();
        const reg_dt = $('#reg_dt').val();

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

        $.ajax({
            type: 'post',
            url: '<c:url value="/comment/write"/>',
            data: JSON.stringify({
                "b_no": b_no,
                "c_writer": c_writer,
                "c_password": c_password,
                "c_contents": c_contents
            }),
            contentType: 'application/json',
            success: function (data) {
                console.log("통신성공" + data);
                if (data === "InsertSuccess") {
                    alert("댓글 등록이 완료되었습니다.")
                    console.log("댓글 등록 완료")

                    $('#c_writer').val("");
                    $('#c_password').val("");
                    $('#c_contents').val("");
                    $('#reg_dt').val(reg_dt);

                    getList();

                } else {
                    alert("실패");
                    console.log("댓글 실패")
                }
            },
            error: function () {
                alert("통신 실패");
            }
        });

    });


    getList();

    function getList() {
        <c:forEach var="board" items="${board}" varStatus="status">
        const b_no = ${board.b_no};
        const count =${board.commentcount}
        </c:forEach>
        const c_writer = $('#c_writer').val();
        const c_password = $('#c_password').val();
        const c_contents = $('#c_contents').val();
        const reg_dt = $('#reg_dt').val();

        <%--        <c:forEach items="${comment}" var="comment">--%>
        $.getJSON(
            "<c:url value='/comment/commentList/'/>" + b_no, function (data) {

                let list = data;
                console.log("댓글 개수: " + list.length);

                let listLength = list.length;
                document.querySelector("#commentBox").innerHTML = "댓글 리스트 " + listLength + "개";


                let comment_html = "<div>";
                for (let i = 0; i < list.length; i++) {


                    let content = list[i].c_contents;
                    let writer = list[i].c_writer;
                    let reg_dt = list[i].reg_dt;

                    comment_html += "<div><span id='c_writer'><strong>" + writer + " / " +  reg_dt + "</strong></span><br/>";
                    comment_html += "<span id='c_contents' class='form-control'>" + content + "</span><br>";

                    comment_html += "</div><hr>";

                }

                $(".commentList").html(comment_html);

            }
        )
        <%--        </c:forEach>--%>
    }



</script>
</body>
</html>
