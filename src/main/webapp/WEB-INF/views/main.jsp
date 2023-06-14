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

    <style type="text/css">
        /* 헤더영역 CSS */
        .header
        {
            border-bottom: darkgray 2px solid;
            display: flex;
            justify-content: space-between;
            width: 60%;
            margin: 0 auto;
        }
        .logoBox
        {
            /*margin: 0 auto;*/
            text-align: center;
            width: 70%;
            padding-bottom: 15px;
        }
        #logo
        {
            font-size: 42px;
            font-weight: bold;
            font-family: 휴먼편지체;
            text-decoration: none;
            color: black;
        }

        .logoBox > span
        {
            margin-top: 15px;
            font-family: 휴먼편지체;
        }

        .loginBox
        {
            margin-top: 15px;
            text-align: right;
        }

        button
        {
            background-color: white;
            font-weight: bold;
            font-family: 휴먼고딕체;
            border: none;
            cursor: pointer;
        }

        .loginBox > button:hover
        {
            color: darkgray;
        }
        /* 헤더영역 CSS 끝 */

        /* 검색창 CSS */
        .search
        {
            display: flex;
            margin-top: 250px;
            justify-content: center;
            margin-bottom: 200px;
        }

        #searchInput
        {
            text-align: left;
            border-radius: 20px;
            border: darkgray solid 2px;
            height: 50px;
            width: 500px;
        }
        #keyword
        {
            background-color: white;
            font-weight: bold;
            font-family: 휴먼고딕체;
            font-size: 20px;
            border: none;
            height: 30px;
            width: 350px;
            margin-left: 40px;
            margin-top: 10px;
            outline: none; /* 클릭 시 외곽선 표시 없애기 */
        }
        #searchInput > button
        {
            margin-left: 40px;
        }
        #searchInput > button > i
        {
            font-size: 30px;
        }
        #searchFilter
        {
            text-align: right;
            margin-left: 60px;
        }
        #searchFilter > select
        {
            border: darkgray 2px solid;
            width: 100px;
            height: 45px;
            font-weight: bold;
            font-family: 휴먼고딕체;
            font-size: 12px;
            margin-top: 4px;
            text-align: center;
        }

        .myItemAndSort
        {
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            width: 60%;
        }

        #myItems
        {
            text-align: left;
            font-weight: bold;
        }

        #sort
        {
            text-align: right;
        }

        #sort > select
        {
            border: black 2px solid;
            width: 100px;
            height: 30px;
            font-weight: bold;
            font-family: 휴먼고딕체;
            font-size: 12px;
            text-align: center;
        }

        label
        {
            /* 더블클릭 시 파란블럭 생성 막기 */
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        /* 검색창 CSS 끝 */

        /* 게시판 영역 CSS 시작*/
        .boardList
        {
            display: flex;
            justify-content: center;
            width: 60%;
            margin: 0 auto;
        }
        #noItem
        {
            color: darkgray;
            font-weight: bold;
            font-family: 휴먼고딕체;
            font-size: 20px;
            text-align: center;
            margin-top : 100px;
        }
        /* 게시판 영역 CSS 끝*/

        /* 페이지 영역 */
        .paging
        {
            display: flex;
            justify-content: space-between;
            width: 60%;
            margin : 0 auto;
        }

        #pages
        {
            margin-top: 100px;
        }

        .paging > button
        {
            margin-top: 100px;
            border: black solid 1px;
            background-color: white;
            text-align: right;
            height: 25px;
        }

        .paging > button:hover
        {
            background-color: wheat;
            color: black;
        }
        /* 페이지 영역 끝*/

        /* 모달영역 시작*/
        .modal
        {
            position: absolute;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.4);
            top:0; left:0;
            display: none;
        }

        .modal-content
        {
            background: white;
            position: absolute;
            top:35%;
            left:40%;
            border: 2px solid black;
            font-weight: bold;
            width: 500px;
            height: 200px;
        }

        #modal-title
        {
            font-weight: bold;
            font-size: 20px;
            border-bottom: 2px solid darkgray;
            padding: 10px;
            margin-bottom: 20px;
        }

        .essential
        {
            color: red;
            margin-right: 20px;
        }

        .regCondition
        {
            font-size: 10px;
            color: darkgray;
            position: absolute;
            left: 300px;
        }

        .modal-content > form
        {
            padding-left: 30px;
        }

        .modal-content > form > div
        {
            margin-bottom: 10px;
        }

        .modal-content > form > div > input
        {
            position: absolute;
            left: 100px;
        }
        #regBtn
        {
            border: black solid 2px;
            margin-left: 140px;
            margin-right: 20px;
        }
        #regBtn:hover
        {
            background: blue;
            color: white;
        }

        #cancel
        {
            border: black solid 2px;
        }
        #cancel:hover
        {
            background: red;
            color: white;
        }
        /* 모달영역 끝*/
    </style>
    <script src="https://kit.fontawesome.com/0fa0e562e0.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            /* 회원가입 성공 알림 */
            if($("#regCheck").val() == 1)
                alert("회원가입 완료!");

            /* 로그인 실패 알림 */
            if($("#loginCheck").val() == "fail")
                alert("로그인 실패.\nID와 PW를 확인해주세요.");

            /* 회원가입 및 로그인 모달창 띄우기 */
            $(".loginBox > button").click(function () {
                // 눌린 버튼의 id 대입
                let btnId = $(this).attr("id");

                // 클릭한 버튼에 따른 모달창 내용 변경
                if(btnId == "login")
                {
                    $("#modal-title").text("로그인");
                    $("#nickname").css("display","none");
                    $("#nickname > input").attr("disabled","disabled");
                    $(".regCondition").css("display","none");
                    $("#regBtn").text("로그인");
                    $("#regBtn").css("marginLeft","100");
                    $("#regBtn").css("marginTop","15");
                    <%--$("#modal-title").next().attr("action",'<c:url value="/login"/>');--%>
                    $("#modal-title").next().attr("method",'get');
                    $(".modal-content").css("width","400px");
                }
                else
                {
                    $("#modal-title").text("회원가입");
                    $("#nickname").css("display","block");
                    $("#nickname > input").removeAttr("disabled");
                    $(".regCondition").css("display","inline");
                    $(".modal-content").css("width","500px");
                    $("#regBtn").css("marginLeft","140");
                    $("#regBtn").css("marginTop","0");
                    $("#regBtn").text("회원가입");
                    <%--$("#modal-title").next().attr("action",'<c:url value="/registration"/>');--%>
                    $("#modal-title").next().attr("method",'post');
                }
                $(".modal").fadeIn();
            });

            /* 회원가입 및 로그인 취소 시 모달창 닫기*/
            $("#cancel").click(function () {
                $(".modal").fadeOut();
            });

            /* 아이디 중복검사 */
            $("#id > input").on("keyup", function () {

                $.ajax({
                    type: "GET",
                    url: "<c:url value='/user/dupCheck'/>",
                    dataType: "text",
                    data: {"id" : $("#id > input").val()},
                    success: function (result) {
                        // 키 누를 때마다 체크 메시지 지우기
                        $("#dupCheck").text("");

                        // id글자수 조건 충족 및 회원가입 모달일 경우.
                        if($("#id > input").val().length >= 6 && $("#modal-title").text() == "회원가입")
                        {
                            // 모달창 늘려주기
                            $(".modal-content").css("height","220px");

                            // 중복된 아이디가 있다면
                            if(result == "duplicated")
                            {
                                // $("#dupCheck").text("중복된 ID입니다.");
                                $("#dupCheck").text("중복!");
                                $("#dupCheck").css("color","red");
                                $("#dupCheck").css("fontSize","11pt");
                                $("#pw").css("marginTop","10px");
                            }
                            else
                            {
                                // $("#dupCheck").text("OK");
                                $("#dupCheck").text("중복검사 통과");
                                $("#dupCheck").css("color","green");
                                $("#dupCheck").css("fontSize","11pt");
                                $("#pw").css("marginTop","10px");
                            }
                        }
                        else
                        {
                            // 메세지 사라지면 모달창 크기 원상복구
                            $(".modal-content").css("height","200px");
                            $("#pw").css("marginTop","0");
                        }
                    },
                    error: function (request) {$("#dupCheck").text("Err");}
                });

            });

            /*회원가입 시 아이디, 비밀번호, 닉네임 검사*/
            $("#regBtn").click(function () {
                let idCheck = /^[a-z0-9+]{6,12}$/;
                let pwCheck = /^[A-Za-z0-9+]{8,15}$/;
                let nickCheck = /^[가-힣a-zA-Z0-9+]{1,15}$/;

                if($(this).parent().attr("method") == "post")
                {
                    // 아이디 검사
                    if(!idCheck.test($("#id > input").val()))
                    {
                        alert("아이디 형식이 올바르지 않습니다.");
                        return;
                    }
                    // 비밀번호 검사
                    if(!pwCheck.test($("#pw > input").val()))
                    {
                        alert("비밀번호 형식이 올바르지 않습니다.");
                        return;
                    }
                    // 닉네임 검사
                    if(!nickCheck.test($("#nickname > input").val()))
                    {
                        alert("닉네임 형식이 올바르지 않습니다.");
                        return;
                    }
                    if($("#dupCheck").text() == "중복!")
                    {
                        alert("아이디가 중복됩니다.");
                        return;
                    }
                }

                // 모든 검사 통과하면 form 태그 제출
                $(this).parent().submit();
            });
        });
    </script>
</head>
<body>
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
                        <button id="login">로그인</button>
                        <button id="registration">회원가입</button>
                    </c:when>
                    <c:otherwise>
                        ${sessionScope.get("nickname")}님 환영합니다! <br>
                        <form action="<c:url value='/user/logout'/>">
                            <button type="submit">로그아웃</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>
    <%-- 헤더 영역 끝--%>

    <%-- 검색창 및 필터 --%>
    <form action='<c:url value="/board"/>' method="get"/>
        <div class="search">
            <div id="searchInput">
                <input type="text" id="keyword" placeholder="검색어를 입력해주세요." name="keyword"/>
                <button type="submit"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></button>
            </div>
            <div id="searchFilter">
                <select name="option">
                    <option value="T">제목</option>
                    <option value="A">제목+내용</option>
                    <option value="U">작성자</option>
                </select>
            </div>
        </div>
    </form>

    <div class="myItemAndSort">
        <div id="myItems">
            <input type="checkbox" name="myItem" id="myItem"/> <label for="myItem">내가 쓴 글만 보기</label>
        </div>

        <div id="sort">
            <select name="sort">
                <option value="date">최신순</option>
                <option value="like">좋아요순</option>
            </select>
        </div>
    </div>
    <%-- 검색창 및 필터 영역 끝 --%>

    <%-- 게시판 영역 --%>
    <div class="boardList">
        <span id="noItem">
            게시물이 존재하지 않습니다.
        </span>
    </div>
    <%-- 게시판 영역 끝--%>

    <%-- 글쓰기 버튼 및 페이지 영역--%>
    <div class="paging">
        <div></div>

        <div id="pages">
           < 1 2 3 4 5 6 7 8 9 10 >
        </div>

        <button id="writeBtn">글쓰기</button>
    </div>
    <%-- 글쓰기 버튼 및 페이지 영역 끝--%>

    <%-- 로그인 및 회원가입 모달창 영역 --%>
    <div class="modal">
        <div class="modal-content">
            <div id="modal-title">회원가입</div>
            <form action='<c:url value="/user"/>' method="post">
                <div id="id"> ID <span class="essential">*</span> <input type="text" name="id"> <span class="regCondition">영어 소문자 + 숫자 / 6글자 ~ 12글자</span> </div>
                <span id="dupCheck"></span>
                <div id="pw"> PW <span class="essential">*</span> <input type="password" name="pw"> <span class="regCondition">영어 대소문자 + 숫자 / 8글자 ~ 15글자</span> </div>
                <div id="nickname"> 닉네임 <span class="essential">*</span> <input type="text" name="nickname"> <span class="regCondition">특수문자 제외 15글자</span> </div>
                <button type="button" id="regBtn">회원가입</button> <button type="reset" id="cancel">취소</button>
            </form>
        </div>
    </div>
    <%-- 로그인 및 회원가입 모달창 영역 끝 --%>

    <%-- 회원가입 성공 여부 확인 --%>
    <input type="hidden" id="regCheck" value="${regResult}">
    <input type="hidden" id="loginCheck" value="${loginCheck}">

</body>
</html>
