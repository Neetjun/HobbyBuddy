<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Neetjun
  Date: 2023-06-15
  Time: 오후 11:20
  To change this template use File | Settings | File Templates.
--%>
<div class="modal-content">
    <div id="modal-title">회원가입</div>
    <form action='<c:url value="/user"/>' method="post">
        <div id="id"> ID <span class="essential">*</span> <input type="text" name="id"> <span class="regCondition">영어 소문자 + 숫자 / 6글자 ~ 12글자</span> </div>
        <span id="dupCheck"></span>
        <div id="pw"> PW <span class="essential">*</span> <input type="password" name="pw"> <span class="regCondition">영어 대소문자 + 숫자 / 8글자 ~ 15글자</span> </div>
        <div id="nickname"> 닉네임 <span class="essential">*</span> <input type="text" name="nickname" id="newNickname"> <span class="regCondition">한글 6글자 / 영어 12글자</span> </div>
        <button type="button" class="submitBtn" id="regBtn">회원가입</button> <button type="reset" class="cancel" id="modalCancel">취소</button>
    </form>
</div>