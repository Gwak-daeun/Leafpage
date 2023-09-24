let loginUserId = $("#loginUserId");
let loginUserPassword= $("#loginUserPassword");
let originalColor = $(".checkText").css("color");

function loginByEnterKey(event) {
    if (event.keyCode === 13) {
        $('#login_btn').click();
    }
}

loginUserId.on('keyup', loginByEnterKey);
loginUserPassword.on('keyup', loginByEnterKey);

//프론트단 로그인 유효성 검사

function loginCheck() {
    let loginCheckSpan = $('.login_check').css("color",originalColor);

    //아이디를 입력하지 않았다면
    if (loginUserId.val() == "") {
        loginCheckSpan.text("※아이디를 입력해주세요.");
        loginUserId.focus();
        return false;
    }
    //비밀번호를 입력하지 않았다면
    if (loginUserPassword.val() == "") {
        loginCheckSpan.text("※비밀번호를 입력해주세요.");
        loginUserPassword.focus();
        return false;
    }

    loginCheckSpan.text("※로그인 정보 확인 중 입니다.").css("color","#056365");

    $.ajax({
        url: 'loginCheck.do',
        type: 'POST',
        async: true,
        data: {
            loginUserId: loginUserId.val(),
            loginUserPassword: loginUserPassword.val(),
        },
        dataType: 'text',
        success: function(data){
            if(data === "-1"){  // [-1]비밀번호 틀림
                console.log("비밀번호 틀림");
                loginCheckSpan.text("※비밀번호가 일치하지 않습니다. 다시 입력해주세요.").css("color",originalColor);
                loginUserPassword.focus();
                return false;
            } else if (data === "-2"){ // [-2]아이디 없음
                console.log("아이디 없음");
                loginCheckSpan.text("※아이디가 존재하지 않습니다. 다시 확인해주세요.").css("color",originalColor);
                loginUserId.focus();
                return false;
            } else {
                $("form[name=login_form]").submit();
            }
        },
        error: function(e){
            alert("오류가 발생했습니다.");
        }
    });
}