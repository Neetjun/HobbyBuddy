<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: Neetjun
  Date: 2023-06-15
  Time: 오후 9:12
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>게시판제목(임시)</title>

    <link href="<c:out value='${pageContext.request.contextPath}'/>/resources/main.css" rel="stylesheet" type="text/css" />
    <script src="https://kit.fontawesome.com/0fa0e562e0.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" hidden="hidden">
        let id = "${sessionScope.get('id')}";
        let nickname = "${sessionScope.get('nickname')}";
        let contextRoot = "<c:url value='/'/>";
        let contextPath = "<c:out value='${pageContext.request.contextPath}'/>";
        let uno = "${sessionScope.get('uno')}";
    </script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/resources/main.js"></script>
</head>
<body>
    <%-- 헤더 --%>
    <c:import url="header.jsp"/>

    <%-- 게시글 작성 및 읽기 영역 --%>
    <form action="<c:url value="/board"/>" method="post">
        <div class="board-area">
            <input type="hidden" value="${boardDTO.bno}" id="bno">
            <input type="hidden" value="${boardDTO.b_uno}" name="b_uno">

            <%-- 게시글 제목 --%>
            <div id="board-title">
                <c:if test="${type ne 'read'}">
                    <span>제목</span> <br>
                </c:if>
                <input type="text" name="title" ${type eq 'read' ? 'readonly=readonly' : ''} value="${boardDTO.title}">
                <div id="writerInfo">
                    <div id="wName">
                        <i class="fa-solid fa-user"></i>
                        ${writer}
                    </div>
                    <div></div>
                    <div id="bDate">${boardDTO.b_reg_date}</div>
                </div>
            </div>

            <%-- 게시글 내용 --%>
            <div id="board-content">
                <%-- 게시글 내용 작성 contenteditable div --%>
                <div id="inputDiv" ${type eq 'read' ? 'contenteditable="false"' : 'contenteditable="true"'}>
                    ${boardDTO.b_content}
                </div>
                <%-- 제출용으로 contenteditable div 내용을 담을 textarea --%>
                <textarea name="b_content" hidden="hidden" ></textarea>
            </div>

            <%-- 좋아요 버튼 영역 --%>
            <c:if test="${type eq 'read'}">
                <div id="likeBtnArea">
                    <i class="fa-regular fa-thumbs-up" id="likeBtn"></i>
                    <span>${boardDTO.like_count}</span>
                </div>
            </c:if>

            <%-- 게시글 버튼 영역 --%>
            <div id="boardBtn">
                <c:choose>
                    <c:when test="${type eq 'read'}">
                        <input type="hidden" value="${isWriter}" id="isWriter">
                        <button id="goList" type="button">목록</button>
                        <button id="goUpdate" type="button">수정</button>
                        <button class="cancel" id="bDelete" type="button">삭제</button>
                    </c:when>
                    <c:otherwise>
                        <button id="imageBtn" type="button">+</button>
                        <input type="file" hidden="hidden" id="imageInput">
                        <button type="button" id="bSubmit" class="submitBtn">등록</button>
                        <button id="bCancel" class="cancel" type="button">취소</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form>

    <c:if test="${type eq 'read'}">
        <div class="comment-area">
            <span>덧글</span>

            <%-- 덧글목록 --%>
            <div id="commentList">
                <form action="<c:url value="/comment"/>" method="post">

                </form>

            </div>

            <c:choose>
                <c:when test="${sessionScope.get('id') ne null}">
                    <%-- 덧글입력창 --%>
                    <div id="cInputArea">
                        <form action="<c:url value="/comment"/>" method="post">
                            <textarea id="cInput" placeholder="깨끗한 덧글 문화를 유지합시다." name="c_content"></textarea>
                            <input type="hidden" name="c_bno" value="${boardDTO.bno}">
                            <input type="hidden" name="c_uno" value="${sessionScope.get("uno")}">
                            <button type="button" class="submitBtn cSubmit">등록</button>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <span id="cLoginReq">덧글은 로그인 후 입력이 가능합니다!</span>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <%-- 헤더 메뉴 전용 모달창 --%>
    <div class="modal">
        <c:import url="modalForHeader.jsp"/>
    </div>

    <%-- 회원가입 및 로그인 성공 여부 확인 --%>
    <input type="hidden" id="regCheck" value="${regResult}">
    <input type="hidden" id="loginCheck" value="${loginCheck}">

</body>
</html>
