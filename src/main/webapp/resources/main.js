$(document).ready(function () {

    let flag = location.pathname.indexOf("board");

    /* 회원가입 성공 알림 */
    if ($("#regCheck").val() == 1)
        alert("회원가입 완료!");

    /* 로그인 실패 알림 */
    if ($("#loginCheck").val() == "fail")
        alert("로그인 실패.\nID와 PW를 확인해주세요.");

    /* 회원가입 및 로그인 모달창 띄우기 */
    $(".loginBox > button").click(function () {
        // 눌린 버튼의 id 대입
        let btnId = $(this).attr("id");

        // 클릭한 버튼에 따른 모달창 내용 변경
        if (btnId == "login") {
            $("#modal-title").text("로그인");
            $("#nickname").css("display", "none");
            $("#nickname > input").attr("disabled", "disabled");
            $(".regCondition").css("display", "none");
            $("#regBtn").text("로그인");
            $("#regBtn").css("marginLeft", "100");
            $("#regBtn").css("marginTop", "15");
            $("#modal-title").next().attr("method", 'get');
            $(".modal-content").css("width", "400px");
        } else {
            $("#modal-title").text("회원가입");
            $("#nickname").css("display", "block");
            $("#nickname > input").removeAttr("disabled");
            $(".regCondition").css("display", "inline");
            $(".modal-content").css("width", "500px");
            $("#regBtn").css("marginLeft", "140");
            $("#regBtn").css("marginTop", "0");
            $("#regBtn").text("회원가입");
            $("#modal-title").next().attr("method", 'post');
        }
        $(".modal").fadeIn();
    });

    /* 회원가입 및 로그인 취소 시 모달창 닫기*/
    $("#modalCancel").click(function () {
        $("#dupCheck").text("");
        $(".modal").fadeOut();
    });

    /* 아이디 중복검사 */
    $("#id > input").on("blur", function () {
        $.ajax({
            type: "GET",
            url: contextRoot + "user/dupCheck",
            dataType: "text",
            data: {"id": $("#id > input").val()},
            success: function (result) {
                // 키 누를 때마다 체크 메시지 지우기
                $("#dupCheck").text("");

                // id글자수 조건 충족 및 회원가입 모달일 경우.
                if ($("#id > input").val().length >= 6 && $("#modal-title").text() == "회원가입") {
                    // 모달창 늘려주기
                    $(".modal-content").css("height", "220px");

                    // 중복된 아이디가 있다면
                    if (result == "duplicated") {
                        $("#dupCheck").text("중복!");
                        $("#dupCheck").css("color", "red");
                        $("#dupCheck").css("fontSize", "11pt");
                        $("#pw").css("marginTop", "10px");
                    } else {
                        // $("#dupCheck").text("OK");
                        $("#dupCheck").text("중복검사 통과");
                        $("#dupCheck").css("color", "green");
                        $("#dupCheck").css("fontSize", "11pt");
                        $("#pw").css("marginTop", "10px");
                    }
                } else {
                    // 메세지 사라지면 모달창 크기 원상복구
                    $(".modal-content").css("height", "200px");
                    $("#pw").css("marginTop", "0");
                }
            },
            error: function (request) {
                $("#dupCheck").text(this.error);
            }
        });
    });

    /*회원가입 시 아이디, 비밀번호, 닉네임 검사*/
    $("#regBtn").click(function () {
        let idCheck = /^[a-z0-9+]{6,12}$/;
        let pwCheck = /^[A-Za-z0-9+]{8,15}$/;
        let nickCheck = /^[가-힣a-zA-Z0-9+]{1,15}$/;

        if ($(this).parent().attr("method") == "post") {
            // 아이디 검사
            if (!idCheck.test($("#id > input").val())) {
                alert("아이디 형식이 올바르지 않습니다.");
                return;
            }
            // 비밀번호 검사
            if (!pwCheck.test($("#pw > input").val())) {
                alert("비밀번호 형식이 올바르지 않습니다.");
                return;
            }
            // 닉네임 검사
            if (!nickCheck.test($("#nickname > input").val())) {
                alert("닉네임 형식이 올바르지 않습니다.");
                return;
            }
            if ($("#dupCheck").text() == "중복!") {
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
        if (btnId == "modNickname") {
            $("#modal-title").text("닉네임 수정");
            $("#nickname").html("<input type='hidden' name='_method' value='PATCH'/>");
            $("#id").html("현재 닉네임 : <b>" + nickname + "</b>");
            $("#id").append("<div>새로운 닉네임 : <input id='newNickname' name='nickname'/> " + "<input type='hidden' value='" + id + "' name='id'/></div>")
            $("#id").append("<input name='mod' value='modify' type='hidden'/>");
            $("#pw").html("");
            $(".regCondition").css("display", "none");
            $("#regBtn").text("수정");
            $("#regBtn").css("marginLeft", "100");
            $("#regBtn").css("marginTop", "15");
            $("#modal-title").next().attr("method", 'post');
            $(".modal-content").css("width", "400px");
        }

        $(".modal").fadeIn();
    });

    // 로그인 여부에 따른 글쓰기 버튼 표시
    if (id != "")
        $("#writeBtnDiv").html("<button id='writeBtn'>글쓰기</button>");
    else
        $("#writeBtnDiv").html("");

    $("#writeBtn").on("click", function () {
        window.location.href = contextRoot + "board";
    });

    // 로그인 여부에 따른 내 글만 보기 감추기
    if (uno == "")
        $("#myItems").html("");

    // 메인화면에서만 ajax들이 작동할 수 있도록 하는 분기문
    if (flag == -1) {
        // 게시글 목록 불러오기 ajax
        function bList() {
            $.ajax({
                type: "GET"
                , url: contextRoot + "board/list"
                , data: {
                    "page": $("#page").val(), "keyword": $("#keyword").val(), "search": $("#option").val(),
                    "sort": $("#sort").val(), "myItem": $("#myItem").prop('checked'), "uno": uno
                }
                , success: function (objMap) {
                    let list = objMap.list;
                    let ph = objMap.ph;

                    // 결과없음 -> 결과있음의 경우 배열 기준을 flex-center에서 flax-start로 변경
                    $(".boardList").css("justifyContent", "flex-start");

                    if (list.length != 0) {
                        $(".boardList").html("");

                        // 게시판 목록 출력하기
                        for (let i = ph.startList - 1; i < ph.endList; i++) {
                            let item = "<div class='boardItem'>";
                            item += "<div class='Thumbnail'>" + "Hobby Buddy" + "</div>";
                            item += "<div class='titleAndInfo'>";
                            item += "<div class='itemTitle'>" + list[i].title + "</div>";
                            item += "<div class='itemInfo'>"
                                + "<i class='fa-solid fa-thumbs-up'></i>"
                                + "<span>" + list[i].like_count + "</span>"
                                + "<i class='fa-solid fa-comment'></i>"
                                + "<span>" + list[i].cmt_count + "</span>"
                                + "<i class='fa-solid fa-eye'></i>"
                                + "<span>" + list[i].view_count + "</span>";
                            item += "</div>" // itemInfo 끝
                            item += "</div>" // titleAndInfo 끝
                            item += "<input hidden='hidden' value='" + list[i].bno + "'/>"
                            item += "</div>"; // boardItem 끝
                            $(".boardList").append(item);
                        }

                        // 페이징 처리
                        $("#page").val(ph.page);
                        let pages = "";
                        if (ph.startPage > 10)
                            pages += "<i class=\'fa-solid fa-angle-left\' id='" + ph.prevPageStart + "'></i>";

                        for (let j = ph.startPage; j <= ph.endPage; j++) {
                            if (j == $("#page").val())
                                pages += "<span id='curPage'>" + j + "</span>";
                            else
                                pages += "<span id='" + j + "'>" + j + "</span>";
                        }

                        if (ph.totPage > 10 && ph.totPage != ph.endPage)
                            pages += "<i class=\"fa-solid fa-angle-right\" id='" + ph.nextPageStart + "'></i>";
                        $("#pages").html(pages);
                    } else {
                        $(".boardList").html($(".boardList").next().html()); // noItem을 감싸고있는 Div의 html 내용 가져오기
                        $(".boardList").css("justifyContent", "center");
                        $("#noItem").removeAttr("hidden");
                    }

                }
                , error: function (request, status, error) {
                    alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }
            });
        }

        $("#myItem").click(function () {
            bList();
        });

        bList();

        // 게시글 검색
        $("#searchInput > button").click(function () {
            // alert($("#keyword").val());
            // alert($("#option").val());
            // alert($("#sort").val());
            bList();
        });

        // 게시글 페이지 이동
        $(document).on("click", "#pages > span", function () {
            if ($(this).attr("id") == "curPage")
                return;

            let newPage = $(this).attr("id");
            $("#page").val(newPage);
            $("#curPage").attr("id", $("#curPage").text());
            $(this).attr("id", "curPage");
            bList();
        });

        // 게시글 페이지 목록 변경 (ex : 1 ~ 10 -> 11 ~ 20)
        $(document).on("click", "#pages > i", function () {
            let newPage = $(this).attr("id");
            $("#page").val(newPage);
            bList();
        })

        // 게시글 읽기
        // ajax 이벤트가 안먹히므로 아래와 같이 코드 변경
        // $(."boardItem").on("click", function () {
        $(document).on("click", ".boardItem", function () {
            let bno = $(this).children("input").val()
            window.location.href = contextRoot + "board/" + bno;
        });

        $("#sort").change(function () {
            bList();
        });
    }

    /* ----- BoardForm.jsp -------- */
    if(flag != -1)
    {
        $("#bSubmit").click(function () {
            $("#board-content > textarea").text($("#inputDiv").html());
            $(this).parent().parent().submit();
        });

        $("#bCancel").click(function () {
            window.location.href = contextRoot;
        });

        // 게시판 글쓰기 입력 영역 크기 자동 조절
        $("#inputDiv").keyup(function () {
            let newHeight = $(this).prop("scrollHeight") - 40;
            // let newHeight = $(this).css("height");

            if (newHeight > 400) {
                $(this).css("height", "auto");
                // $(this).css("height",newHeight+"px");
                // $(this).css("overflow-y","hidden");
            }
        });

        // 게시판 읽기모드일 때 디자인 변경
        if ($("#board-title > input").attr("readonly") == "readonly") {
            let title = $("#board-title > input");
            let content = $("#inputDiv");
            let writerInfo = $("#writerInfo");
            title.css("border", "0");
            title.css("fontSize", "16pt");
            content.css("border", "0");
            content.css("height", "auto");
            writerInfo.css("display", "block");
        }

        // 좋아요 기능
        $("#likeBtn").click(function () {
            if (uno == "") {
                alert("좋아요는 로그인 후 가능합니다.");
                return;
            } else
                $.ajax({
                    type: "POST"
                    , url: contextRoot + "board/like"
                    , data: {"bno": $("#bno").val(), "uno": uno}
                    , success: function (result) {
                        if (result == "success")
                            window.location.href = contextRoot + "board/" + $("#bno").val();
                        else
                            alert("좋아요는 한 번만 가능합니다.")
                    }
                    , error(request) {
                        console.log(request.responseText);
                    }
                })
        });

        // 작성자 여부에 따른 수정, 삭제버튼 노출 여부
        if ($("#isWriter").val() != "true") {
            $("#goUpdate").css("display", "none");
            $("#bDelete").css("display", "none");
            $("#goList").css("marginLeft", "940px");
        }

        // 목록으로 돌아가기
        $("#goList").click(function () {
            window.location.href = contextRoot;
        });

        // 게시글 삭제하기
        $("#bDelete").click(function () {

            if (confirm("정말로 삭제하시겠습니까?")) {
                // alert("확인");
                let formObj = $(this).parent().parent().parent();
                let bno = $("#bno").val();
                formObj.attr("action", contextRoot + "board/" + bno + "?mod=delete");
                formObj.submit();
            } else
                return;
        });

        // 게시글 수정 폼으로 변경
        $("#goUpdate").click(function () {
            // form태그 action 속성 변경
            let formObj = $(this).parent().parent().parent();
            let bno = $("#bno").val();
            formObj.attr("action", contextRoot + "board/" + bno + "?mod=modify");

            // 디자인 복구 및 readonly 속성 해제
            let title = $("#board-title > input");
            let content = $("#inputDiv");
            let writerInfo = $("#writerInfo");
            title.css("border", "1px solid gray");
            title.css("fontSize", "12pt");
            $("#board-title").prepend("<span>제목</span> <br>");
            content.css("border", "1px solid gray");
            content.css("height", "auto");
            writerInfo.css("display", "none");
            title.removeAttr("readonly");
            content.attr("contentEditable", "true");
            $("#likeBtnArea").css("display", "none");


            // 버튼 구성요소 변경
            $("#goList").css("display", "none");
            $("#bDelete").css("display", "none");
            $(this).css("display", "none");
            $(this).parent().prepend("<button id='uCancel' class='cancel' type='button'>취소</button>");
            $(this).parent().prepend("<button id='bUpdate' type='button'>수정</button> ");

            // 덧글 영역 감추기
            $(".comment-area").css("display", "none");
        });

        // 게시글 수정하기
        $(document).on("click", "#bUpdate", function () {
            $("#board-content > textarea").text($("#inputDiv").html());
            $(this).parent().parent().parent().submit();
        });

        // 게시글 수정 취소
        $(document).on("click", "#uCancel", function () {
            window.location.href = contextRoot + "board/" + $("#bno").val();
        });

        // 이미지 첨부 기능
        $("#imageBtn").click(function () {
            $("#imageInput").click();
        });

        // 덧글 입력창 크기 자동조절
        $("#cInput").keyup(function () {
            // 스크롤 높이 가져오기
            let newHeight = $(this).prop("scrollHeight") - 4;

            /* 단위까지 가져오는 방법 */
            // let currentHeight = $(this).css("height");

            /* 단위를 제외해서 가져오는 방법 */
            let currentHeight = $(this).height();

            if (newHeight > currentHeight) {
                $(this).css("height", newHeight + 15 + "px");
                // $(this).css("height",newHeight+"px");
                // $(this).css("overflow-y","hidden");
            }
        });

        // 덧글 입력
        $(document).on("click", ".cSubmit", function () {

            if($("#cInput").val().trim().length == 0)
            {
                alert("덧글을 입력해주세요.");
                return;
            }

            if($(this).parent().attr("class") == "replyForm")
            {
                let tcno = $(this).parent().parent().prev().clone();
                $(this).parent().append(tcno);
            }


            $(this).parent().submit();
        });

        // 대댓글 입력
        $(document).on("click", ".reply > span", function () {
            let spanObj = $(this);
            let tgtCmtArea = $(this).parent().parent().parent(); // cmtItem 요소
            let replyInput = $(this).parent().parent().parent().parent().parent().next().clone();

            if(uno == "")
            {
                alert("답글은 로그인 후 남길 수 있습니다.");
                return;
            }
            else if(spanObj.text() == "답글쓰기")
            {
                spanObj.text("답글취소");
                replyInput.css("border-top","1px solid gray");
                replyInput.css("margin-top","15px");
                replyInput.children("form").attr("class","replyForm");
                replyInput.children("form").css("margin-top","10px");
                replyInput.children("form").prepend("<span class='replyBlank'>ㄴ</span>");
                replyInput.children("form").children("textarea").css("margin-top","15px");
                replyInput.children("form").children("button").css("margin-top","30px");
                tgtCmtArea.append(replyInput);

                $(".replyBlank").css("font-weight", "bold");
                $(".replyBlank").css("color", "gray");
                $(".replyBlank").css("font-size", "25px");
                $(".replyBlank").css("margin-left", "25px");
                $(".replyBlank").css("margin-right", "15px");
                $(".replyBlank").css("margin-top", "20px");
            }
            else if(spanObj.text("답글취소"))
            {
                spanObj.text("답글쓰기");
                tgtCmtArea.find("#cInputArea").remove();
            }
            // }
        });

        // 덧글 가져오기
        $.ajax({
            type: "GET"
            , url: contextRoot + "comment"
            , data: {"bno": $("#bno").val()}
            , success: (function (cmtList) {

                let html = "";

                if(cmtList.length != 0)
                    for (let i = 0; i < cmtList.length; i++) {
                        let hidden = cmtList[i].c_uno == uno ? "" : "hidden='hidden'";
                        let replyPadding = cmtList[i].tcno == null ? "" : "style='padding-left:80px'";
                        let replyDelBtn = cmtList[i].tcno == null ? "" : "style='margin-left:870px'";
                        let isReply = cmtList[i].tcno == null ? cmtList[i].cno : cmtList[i].tcno;
                        html += "<div class='cmtItem' "+replyPadding+">";
                        // 덧글 내용
                        html += "<div id='c-content'>" + cmtList[i].c_content + "</div>";
                        // 덧글 프로필
                        html += "<div id='c-profile' class='profile'>";
                        html += "<div class='profile-image'><img src=" + contextRoot + "resources/image/profile.jpeg/></div>";
                        html += "<div id='cWriter'>" + cmtList[i].nickname + "</div>"
                        html += "<div id='cDate'>" + cmtList[i].c_reg_date + "</div>"
                        html += "<div class='reply'><span>답글쓰기</span></div>"
                        html += "</div>";
                        //덧글 삭제버튼
                        html += "<button type='button' class='cancel cDelete' value='"
                            + cmtList[i].cno + "'" + hidden + " " + replyDelBtn +">삭제</button>";
                        html += "<input name='tcno' class='tcno' hidden='hidden' value="+"'"+ isReply + "'"+"/>"
                        html += "</div>";
                    }
                else {
                    html += "<div id='noComment'>등록된 덧글이 없습니다.</div>";
                }

                $("#commentList > form").append(html);
            })
            , error: (function (request) {
                console.log(request.responseText);
            })
        });

        // 덧글 삭제
        $(document).on("click", ".cDelete", function () {
            // 인풋태그에 해당 버튼의 value (cno)값 넘기기
            $(this).parent().parent().children("input").val($(this).val());

            // form태그 제출
            $(this).parent().parent().submit();
        });
    }
});