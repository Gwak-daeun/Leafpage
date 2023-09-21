const emailCheckRule = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
const phoneCheckRule = /^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/;


function updateMyInfo() {
    let current_user_tel = $('#current_user_tel');
    let current_user_email = $('#current_user_email');
    let checkSpan_for_email_update = $('#checkSpan_for_email_update');
    let checkSpan_for_tel_update = $('#checkSpan_for_tel_update');

    if(current_user_tel.val() === "${userTel}" && current_user_email.val() === "${userEmail}") {
        location.href="index.jsp";
        alert("변경된 정보가 없습니다.");
        return false;
    }
    if(current_user_tel.val() != "${userTel}") {
        if (current_user_tel.val() == "") {
            checkSpan_for_tel_update.html('휴대전화번호를 입력하세요.');
            current_user_tel.focus();
            return false;
        } else if (!phoneCheckRule.test(current_user_tel.val())) {
            checkSpan_for_tel_update.html('휴대전화번호가 올바르지 않습니다.');
            current_user_tel.focus();
            return false;
        }
    }
    if(current_user_email.val() != "${userEmail}") {
        if(current_user_email.val() == "") {
            checkSpan_for_email_update.html('이메일을 입력하세요.');
            current_user_email.focus();
            return false;
        } else if (!emailCheckRule.test(current_user_email.val())) {
            checkSpan_for_email_update.html('이메일이 올바르지 않습니다.');
            current_user_email.focus();
            return false;
        }
    }
    $("form[name=update_form]").submit();
    alert("정보변경이 완료되었습니다.");
}


function withdrawal() {
    console.log("js-withdrawal()진입")
    let checkPassword = $('#checkPassword');
    let checkSpanForWithdrawal = $('#checkSpanForWithdrawal');

    $.ajax({
        url: 'withdrawal.do',
        type: 'POST',
        async: true,
        data: {
            checkPassword: checkPassword.val()
        },
        dataType: 'text',
        success: function(data){
            if(data === "0") {
                checkSpanForWithdrawal.html("입력하신 정보가 맞지 않습니다.");
            }
            else if(data === "1") {
                location.href = "index.jsp";
                alert("정상적으로 탈퇴되었습니다.")
            }
            else if(data === "-1") {
                alert("오류가 발생했습니다.");
            }
        },
        error: function(e){
            alert("오류가 발생했습니다.");
        }
    });
}

