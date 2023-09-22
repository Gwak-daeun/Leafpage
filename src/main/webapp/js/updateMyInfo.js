const emailCheckRule = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
const phoneCheckRule = /^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/;

let current_user_tel = $('#current_user_tel');
let current_user_email = $('#current_user_email');
let checkSpan_for_email_update = $('#checkSpan_for_email_update');
let checkSpan_for_tel_update = $('#checkSpan_for_tel_update');
let password_for_update = $('#password_for_update');
let checkSpan_password_for_update = $('#checkSpan_password_for_update');

function updateMyInfo() {
    if(current_user_tel.val() === currentTel && current_user_email.val() === currentEmail) {
        alert("변경된 정보가 없습니다.");
        return false;
    }
    if(current_user_tel.val() !== currentTel) {
        if (current_user_tel.val() === "") {
            checkSpan_for_tel_update.html('휴대전화번호를 입력하세요.');
            current_user_tel.focus();
            return false;
        } else if (!phoneCheckRule.test(current_user_tel.val())) {
            checkSpan_for_tel_update.html('휴대전화번호가 올바르지 않습니다.');
            current_user_tel.focus();
            return false;
        }
    }
    if(current_user_email.val() !== currentEmail) {
        if(current_user_email.val() === "") {
            checkSpan_for_email_update.html('이메일을 입력하세요.');
            current_user_email.focus();
            return false;
        } else if (!emailCheckRule.test(current_user_email.val())) {
            checkSpan_for_email_update.html('이메일이 올바르지 않습니다.');
            current_user_email.focus();
            return false;
        }
    }
    if(password_for_update.val() !== "") {
        console.log(current_user_tel.val());
        console.log(current_user_email.val());
        console.log(password_for_update.val());
        updateFormSubmit(current_user_tel.val(), current_user_email.val(), password_for_update.val());
    } else {
        checkSpan_password_for_update.html("현재 비밀번호를 입력해주세요.");
    }

}

function updateFormSubmit(userTel, userEmail, userPassword) {
    checkSpan_for_tel_update.html("");
    checkSpan_for_email_update.html("");
    checkSpan_password_for_update.html("");
    $.ajax({
        url: 'updateUserInfo.do',
        type: 'POST',
        async: true,
        data: {
            inputUserTel:userTel,
            inputUserEmail:userEmail,
            passwordForUpdate:userPassword
        },
        dataType: 'text',
        success: function(data) {
            let responseMsg = JSON.parse(data);
            if (responseMsg.duplicateTelError != null) {
                checkSpan_for_tel_update.html(responseMsg.duplicateTelError);
                return false;
            }
            if (responseMsg.duplicateEmailError != null) {
                checkSpan_for_email_update.html(responseMsg.duplicateEmailError);
                return false;
            }
            if (responseMsg.passwordError != null) {
                checkSpan_password_for_update.html(responseMsg.passwordError);
                return false;
            }
            if (responseMsg.success != null) {
                // alert("정보변경이 완료되었습니다. 이메일 재인증이 필요합니다.");
                location.href = "index.jsp";
            }
        },
        error: function(e){
            alert("오류가 발생했습니다.");
        }
    });
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

function inactive() {
    let confirmInactive = confirm("휴면회원으로 전환하시겠습니까?");
    if (confirmInactive) {
        location.href = "inactive.do";
    }
}