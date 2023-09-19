//프론트단 로그인 유효성 검사

function loginCheck() {
    let loginUserId = $("#loginUserId");
    let loginUserPassword= $("#loginUserPassword");

    //아이디를 입력하지 않았다면
    if (loginUserId.val() == "") {
        $('.login_check').remove();
        $('.login_check_area').html("<span class='login_check' style='color:red'>※아이디를 입력해주세요.</span>");
        loginUserId.focus();
        return false;
    }
    //비밀번호를 입력하지 않았다면
    if (loginUserPassword.val() == "") {
        $('.login_check').remove();
        $('.login_check_area').html("<span class='login_check' style='color:red'>※비밀번호를 입력해주세요.</span>");
        loginUserPassword.focus();
        return false;
    }

    $('.login_check').remove();
    $('.login_check_area').html("<span class='login_check' style='color:gray'>※확인중입니다.</span>");


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
            // [-1]비밀번호 틀림, [-2]아이디 없음
            if(data === "-1"){
                console.log("비밀번호 틀림");
                $('.login_check').remove();
                $('.login_check_area').html("<span class='login_check' style='color:red'>※비밀번호가 틀렸습니다.</span>");
                loginUserPassword.focus();
                return false;
            } else if (data === "-2"){
                console.log("아이디 없음");
                $('.login_check').remove();
                $('.login_check_area').html("<span class='login_check' style='color:red'>※없는 아이디 입니다.</span>");
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