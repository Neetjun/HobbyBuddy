<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Neetjun
  Date: 2023-06-11
  Time: 오후 10:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>HobbyBuddy</title>

    <link href="<c:out value='${pageContext.request.contextPath}'/>/resources/main.css" rel="stylesheet" type="text/css" />
    <script src="https://kit.fontawesome.com/0fa0e562e0.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript">
        let id = "${sessionScope.get('id')}";
        let nickname = "${sessionScope.get('nickname')}";
        let contextRoot = "<c:url value='/'/>";
        let uno = "${sessionScope.get('uno')}";
    </script>
    <script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/resources/main.js"></script>
</head>
<body>
    <c:import url="header.jsp"></c:import>

    <%-- 검색창 및 필터 --%>
<%--    <form action='<c:url value="/board"/>' method="get"/>--%>
        <div class="search">
            <div id="searchInput">
                <input type="text" id="keywordInput" placeholder="검색어를 입력해주세요." name="keyword"/>
                <input type="hidden" id="keyword">
                <button type="submit"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></button>
            </div>
            <div id="searchFilter">
                <select name="option" id="option">
                    <option value="T">제목</option>
                    <option value="A">제목+내용</option>
                    <option value="U">작성자</option>
                </select>
            </div>
        </div>
<%--    </form>--%>

    <div class="myItemAndSort">
        <div id="myItems">
            <input type="checkbox" name="myItem" id="myItem"/> <label for="myItem">내가 쓴 글만 보기</label>
        </div>

        <div id="sortArea">
            <select name="sort" id="sort">
                <option value="bno">최신순</option>
                <option value="like_count">좋아요순</option>
            </select>
        </div>
    </div>
    <%-- 검색창 및 필터 영역 끝 --%>

    <%-- 게시판 영역 --%>
    <div class="boardList"></div>
    <div>
        <span id="noItem" hidden="hidden">
            게시물이 존재하지 않습니다.
        </span>
    </div>

    <%-- 게시판 영역 끝--%>

    <%-- 글쓰기 버튼 및 페이지 영역--%>
    <div class="paging">
        <div></div>

        <div id="pages"></div>
        <input type="text" id="page" hidden="hidden" value="1">
        <div id="writeBtnDiv"></div>
    </div>
    <%-- 글쓰기 버튼 및 페이지 영역 끝--%>

    <%-- 로그인 및 회원가입 모달창 영역 --%>
    <div class="modal">
        <c:import url="modalForHeader.jsp"/>
    </div>
    <%-- 로그인 및 회원가입 모달창 영역 끝 --%>

    <%-- 회원가입 및 로그인 성공 여부 확인 --%>
    <input type="hidden" id="regCheck" value="${regResult}">
    <input type="hidden" id="loginCheck" value="${loginCheck}">
    <input type="hidden" id="unAuthErr" value="${unAuthErr}">
</body>
</html>
