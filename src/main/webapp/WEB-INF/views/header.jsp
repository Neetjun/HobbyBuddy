<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%-- 헤더 영역 --%>
<header>
    <div class="header">
        <div></div>
        <div class="logoBox">
            <a href='<c:url value="/"/>' id="logo">Hobby Buddy</a> <br>
        </div>
        <div class="loginBox">
            <c:choose>
                <c:when test='${sessionScope.get("id") eq null}'>
                    <button id="login" class=".loginBoxBtn">로그인</button>
                    <button id="registration" class=".loginBoxBtn">회원가입</button>
                </c:when>
                <c:otherwise>
                    <b>${sessionScope.get("nickname")}</b> 님 환영합니다! <br>
                    <form action="<c:url value='/user/logout'/>">
                        <button type="button" class=".loginBoxBtn" id="modNickname">닉네임수정</button>
                        <button type="submit" class=".loginBoxBtn">로그아웃</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>
<%-- 헤더 영역 끝--%>