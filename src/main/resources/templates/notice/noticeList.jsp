<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>게시물 등록</title>
    <%-- jQuery --%>
    <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.1.9.2.min.js' />"></script>

    <%-- bootstrap --%>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">

    <script type="text/javascript">

        function test() {
            $.ajax({
                url: "<c:url value='/api/userStatus' />",
                type: 'get',
                data: "",
                contentType: false,
                processData: false,
                dataType: 'json'
            }).done(function (data) {
                //로딩바
                console.log(data);

            }).fail(function (jqXHR, textStatus, errorThrown) {
                //로딩바
                console.log(jqXHR, textStatus, errorThrown);
            });
        }


    </script>
</head>
<body>


<div class="container">

    <h2 class="container my-3">게시판</h2>

<%--    <a href="javascript:test();">TEST</a>--%>

    <%--  https://codepen.io/jamesbarnett/pen/kQebQO  --%>
    <div class="marquee container">
        <div>
            <span>제목 옆 'New~' 아이콘은 하루가 지나면 사라집니다! 😊</span>
            <span>제목 옆 'New~' 아이콘은 하루가 지나면 사라집니다! 😊</span>
        </div>
    </div>


    <%--    <form id="listForm"  method="get">--%>
    <form id="listForm" name="listForm" method="post">
        <input type="hidden" id="pageNum" name="pageNum">
        <div class="container my-3">
            <table class="table">
                <thead class="table-danger">
                <tr>
                    <th>ID</th>
                    <th>WRITER</th>
                    <th>TITLE</th>
                    <th>time</th>
                    <th>hits</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="item" items="${board}" varStatus="status">
                    <tr>
                        <td>${item.b_no}</td>
                        <td>${item.b_writer}</td>

                        <td><a href="/guide/page/${item.b_no}">${item.b_title}</a> [${item.commentcount}] <c:if
                                test="${item.REG_DT >= nowday}"><img
                                src="<c:url value='/images/new.png' />" alt=""/></c:if></td>

                        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${item.b_hits}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <a href="/guide/page/noticeResister" class="btn btn-primary">글쓰기</a>
        </div>


        <%-- 페이지네이션 --%>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">


                <li class="page-item pre">
                    <a class="page-link" href="javascript:fnPageClick(${pageNum-1});" aria-label="Previous">
                        <span aria-hidden="true">≪</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pages}" var="x">

                    <li class="page-item">
                        <a class="page-link" id="${x}" href="javascript:fnPageClick(${x });">${x }</a>
                    </li>

                </c:forEach>


                <%--                <li class="page-item">--%>
                <%--                    <c:forEach begin="1" end="${pages }" var="x">--%>
                <%--                    <a class="page-link" id="${x}" href="javascript:fnPageClick(${x });">${x }</a>--%>
                <%--                </li>--%>
                <%--                    </c:forEach>--%>


                <%--                    <a class="page-link" href="#">1</a></li>--%>

                <li class="page-item next">
                    <a class="page-link" href="javascript:fnPageClick(${pageNum+1} )" aria-label="Next">
                        <sapn aria-hidden="true">≫</sapn>
                    </a>
                </li>
            </ul>
        </nav>

    </form>
</div>


<script>

    // 페이징 처리
    function fnPageClick(num) {


        let newImage = document.querySelector("#imgIcon");

        if (num)

            $('#pageNum').val(num);

        console.log(num)
        if (num < 1) {
            let elements = document.getElementsByClassName('pre');
            for (let i = 0; i < elements.length; i++) {
                elements[i].classList.add('disabled');
            }
            return;
        }

        <c:set value="${pages}" var="pages" />

        if (num > ${pages}) {

            console.log(num);
            let elements = document.getElementsByClassName('next');
            for (let i = 0; i < elements.length; i++) {
                elements[i].classList.add('disabled');
            }
            return;
        }

        $('#listForm').attr("action", "<c:url value='/page/noticeList'/>").submit();

    }


</script>

</body>
</html>