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
    </script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/resources/main.js"></script>
</head>
<body>
    <c:import url="header.jsp"/>
    <form action="<c:url value="/board"/>" method="post">
        <div class="board-area">
            <div id="board-title">
                <c:if test="${type ne 'read'}">
                    <span>제목</span> <br>
                </c:if>
                <input type="text" name="title" ${type eq 'read' ? 'readonly=readonly' : ''} value="${boardDTO.title}">
                <div id="writerInfo">
                    <i class="fa-solid fa-user"></i>
                    <span id="wName">${writer}</span>
                    <span id="bDate">${boardDTO.b_reg_date}</span>
                </div>
                ${dto.b_reg_date}
            </div>
            <div id="board-content">
                <div  id="inputDiv" ${type eq 'read' ? 'contenteditable="false"' : 'contenteditable="true"'}>
                    ${boardDTO.b_content}
                </div>
                <textarea name="b_content" hidden="hidden" ></textarea>
            </div>
            <div id="boardBtn">
                <c:choose>
                    <c:when test="${type eq 'read' || type eq 'update'}">
                        <button id="goList" type="button">목록</button>
                        <button id="updateBoard" type="button">수정</button>
                        <button class="cancel" id="deleteBoard" type="button">삭제</button>
                    </c:when>
                    <c:otherwise>
                        <button id="imageBtn" type="button">+</button>
                        <input type="file" hidden="hidden" id="imageInput">
                        <button id="bSubmit" class="submitBtn">등록</button>
                        <button id="bCancel" class="cancel" type="button">취소</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form>



    <div class="modal">
        <c:import url="modalForHeader.jsp"/>
    </div>
</body>
</html>
