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
        $("#modal-title").next().attr("method",'post');
        }
        $(".modal").fadeIn();
    });

    /* 회원가입 및 로그인 취소 시 모달창 닫기*/
    $("#modalCancel").click(function () {
        $("#dupCheck").text("");
        $(".modal").fadeOut();
    });

    /* 아이디 중복검사 */
    $("#id > input").on("keyup", function () {
        $.ajax({
            type: "GET",
            url: contextRoot+"user/dupCheck",
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

    // 닉네임 수정 기능
    $("#modNickname").click(function () {
        let btnId = $(this).attr("id");

        // 클릭한 버튼에 따른 모달창 내용 변경
        if(btnId == "modNickname")
        {
            $("#modal-title").text("닉네임 수정");
            $("#nickname").html("<input type='hidden' name='_method' value='PATCH'/>");
            $("#id").html("현재 닉네임 : <b>"+nickname+"</b>");
            $("#id").append("<div>새로운 닉네임 : <input id='newNickname' name='nickname'/> " + "<input type='hidden' value='"+ id +"' name='id'/></div>")
            $("#id").append("<input name='mod' value='modify' type='hidden'/>");
            $("#pw").html("");
            $(".regCondition").css("display","none");
            $("#regBtn").text("수정");
            $("#regBtn").css("marginLeft","100");
            $("#regBtn").css("marginTop","15");
            $("#modal-title").next().attr("method",'post');
            $(".modal-content").css("width","400px");
        }

        $(".modal").fadeIn();
    });

    // 로그인 여부에 따른 글쓰기 버튼 표시
    if(id != "")
        $("#writeBtnDiv").html("<button id='writeBtn'>글쓰기</button>");
    else
        $("#writeBtnDiv").html("");

    $("#writeBtn").on("click", function () {
        window.location.href = contextRoot+"board";
    });

    // 게시글 목록 불러오기 ajax
    $.ajax({
        type : "GET"
        , url : contextRoot+"board/list"
        , data: {"page":$("#page").val(),"keyword":$("#keyword").val(),"option":$("#option").val()}
        , success : function (list) {
            if(list != null)
            {
                $(".boardList").html("");

                for(let i = 0; i < list.length; i++)
                {
                    let item = "<div id='boardItem'>";
                    item += "<div id='Thumbnail'>" + "Hobby Buddy" + "</div>";
                    item += "<div id='titleAndInfo'>";
                    item += "<div id='itemTitle'>" + list[i].title + "</div>";
                    item += "<div id='itemInfo'>"
                    + "<i class='fa-solid fa-thumbs-up'></i>"
                    + "<span>" + list[i].like_count + "</span>"
                    + "<i class='fa-solid fa-comment'></i>"
                    + "<span>" + 0 + "</span>"
                    + "<i class='fa-solid fa-eye'></i>"
                    + "<span>" + list[i].view_count + "</span>";
                    item += "</div>" // itemInfo 끝
                    item += "</div>" // titleAndInfo 끝
                    item += "</div>"; // boardItem 끝
                    $(".boardList").append(item);
                }
            }
            else
                $("#noItem").removeAttr("hidden");

        }
        , error : function (request, status, error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    })

    /* ----- BoardForm.jsp -------- */

    $("#bSubmit").click(function () {
        $("#board-content > textarea" ).text($("#inputDiv").html());
        $(this).parent().parent().submit();
    });

    $("#bCancel").click(function () {
        window.location.href = contextRoot;
    });

    // 게시판 글쓰기 입력 영역 크기 자동 조절
    $("#inputDiv").keyup(function () {
        let newHeight = $(this).prop("scrollHeight") - 40;
        // let newHeight = $(this).css("height");

        if(newHeight > 400)
        {
            $(this).css("height","auto");
            // $(this).css("height",newHeight+"px");
            // $(this).css("overflow-y","hidden");
        }
    });

    // 게시판 읽기모드일 때 디자인 변경
    if($("#board-title > input").attr("readonly") == "readonly")
    {
        let title = $("#board-title > input");
        let content = $("#inputDiv");
        let writerInfo = $("#writerInfo");
        title.css("border","0");
        title.css("fontSize","16pt");
        content.css("border","0");
        content.css("height","auto");
        writerInfo.css("display","block");
    }
    
    // 이미지 첨부 기능
    $("#imageBtn").click(function () {
        $("#imageInput").click();
    });
});