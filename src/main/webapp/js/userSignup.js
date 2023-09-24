//프론트단 회원가입 유효성 검사
const idCheckRule = /^(?=.*?[a-z,A-Z])(?=.*?[0-9]).{6,12}$/;
const passwordCheckRule = /^(?=.*?[a-z,A-Z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;
const emailCheckRule = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
const phoneCheckRule = /^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/;

let userId = $("#userId");
let userPassword= $("#userPassword");
let userPasswordConfirm=$("#userPasswordConfirm");
let userEmail=$("#userEmail");
let userTel=$("#userTel");
let userSecurityQuestion=$("#userSecurityQuestion");
let userSecurityAnswer=$("#userSecurityAnswer");
let userConsent=$("input[name=userConsent]");

//아이디 중복확인
function duplicateIdCheck() {
    let status = userId.attr('status'); //아이디 중복체크 상태
    $('.checkIdSpan').remove(); //기존에 중복체크한 이력 지워주기

    //아이디를 입력하지 않았다면
    if(userId.val() == "") {
        $('.checkId').after("<span class='checkIdSpan' style='color:gray'>※아이디를 입력해주세요.</span>");
        userId.focus();
        return
    } //아이디가 Rule에 맞지 않는다면
    else if (!idCheckRule.test(userId.val())) {
        $('.checkId').after("<span class='checkIdSpan' style='color:red'>※아이디는 영문자+숫자 조합으로 6자 이상 12자 이하입니다.</span>");
        userId.focus();
        return false;
    }

    $('.checkId').after("<span class='checkIdSpan' style='color:gray'>※확인중입니다.</span>");

    $.ajax({
        url: 'duplicateIdCheck.do',
        type: 'POST',
        async: true,
        data: {
            signupUserId: userId.val()
        },
        dataType: 'text',
        success: function(data){
            //기존 아이디가 존재한다면
            if(data === "1"){
                console.log("사용 불가능한 아이디");
                userId.attr('status', 'false');
                $('.checkIdSpan').remove();
                $('.checkId').after("<span class='checkIdSpan' style='color:red'>※이미 존재하는 아이디입니다.</span>");
                userId.focus();
            //기존 아이디가 존재하지 않으면
            } else if (data === "0"){
                console.log("사용 가능한 아이디");
                userId.attr('status', 'true');
                $('.checkIdSpan').remove();
                $('.checkId').after("<span class='checkIdSpan' style='color:blue'>※사용 가능한 아이디입니다.</span>");
            }
        },
        error: function(e){
            alert("오류가 발생했습니다.");
        }
    });
}

//유효성검사 및 submit
function signupCheck() {
    if(userId.val() == "") {
        alert("아이디를 입력하세요.");
        userId.focus();
        return false;
    } else if (!idCheckRule.test(userId.val())) {
        alert("아이디는 영문자+숫자 조합으로 6자 이상 12자 이하입니다.");
        userId.focus();
        return false;
    }

    if(!passwordCheckRule.test(userPassword.val())) {
        $('.checkPasswordSpan').remove();
        $('.checkPassword').after("<span class='checkPasswordSpan' style='color:red'>비밀번호는 영문자+숫자+특수문자 조합으로 8자리 이상입니다.</span>");
        userPassword.focus();
        return false;
    }

    if(userPassword.val() !== userPasswordConfirm.val()) {
        $('.checkPasswordConfirmSpan').remove();
        $('.checkPasswordConfirm').after("<span class='checkPasswordConfirmSpan' style='color:red'>비밀번호가 일치하지 않습니다.</span>");
        userPasswordConfirm.focus();
        return false;
    }

    if (userEmail.val() == "") {
        $('.checkEmailSpan').remove();
        $('.checkEmail').after("<span class='checkEmailSpan' style='color:red'>이메일 주소를 입력하세요.</span>");
        userEmail.focus();
        return false;
    } else if (!emailCheckRule.test(userEmail.val())) {
        $('.checkEmailSpan').remove();
        $('.checkEmail').after("<span class='checkEmailSpan' style='color:red'>이메일 형식이 올바르지 않습니다.</span>");
        userEmail.focus();
        return false;
    }

    if(userTel.val() == "") {
        $('.checkTelSpan').remove();
        $('.checkTel').after("<span class='checkTelSpan' style='color:red'>휴대전화번호를 입력하세요.</span>");
        userTel.focus();
        return false;
    } else if (!phoneCheckRule.test(userTel.val())) {
        $('.checkTelSpan').remove();
        $('.checkTel').after("<span class='checkTelSpan' style='color:red'>휴대전화번호가 올바르지 않습니다.</span>");
        userTel.focus();
        return false;
    }

    if (userSecurityQuestion.val() == "") {
        $('.checkSecurityQuestionSpan').remove();
        $('.checkSecurityQuestion').after("<span class='checkSecurityQuestionSpan' style='color:red'>비밀번호 찾기 질문을 선택해주세요.</span>");
        userSecurityQuestion.focus();
        return false;
    }

    if (userSecurityAnswer.val() == "") {
        $('.checkSecurityAnswerSpan').remove();
        $('.checkSecurityAnswer').after("<span class='checkSecurityAnswerSpan' style='color:red'>비밀번호 찾기 답을 입력하세요.</span>");
        userSecurityAnswer.focus();
        return false;
    }

    if(!userConsent.is(":checked")){
        alert("개인정보수집 약관에 동의해주세요.")
        userConsent.focus();
        return false;
    }

    let status = userId.attr('status'); //아이디 중복체크 상태
    if(status === "" || status === null){
        alert("아이디 중복체크를 해주세요.");
        userId.focus();
    }else if(status === "false"){
        alert("다른 아이디를 입력해주세요.");
        userId.focus();
    }else if(status === "true"){
        signupSubmit(userId.val(), userPassword.val(), userEmail.val(), userTel.val(), userSecurityQuestion.val(), userSecurityAnswer.val());
    }
}

function signupSubmit(userId, userPassword, userEmail, userTel, userSecurityQuestion, userSecurityAnswer) {
    $.ajax({
        url: 'signup.do',
        type: 'POST',
        async: true,
        data: {
            userId: userId,
            userPassword: userPassword,
            userEmail: userEmail,
            userTel: userTel,
            userSecurityQuestion: userSecurityQuestion,
            userSecurityAnswer: userSecurityAnswer
        },
        dataType: 'text',
        success: function (data) {
            let responseMsg = JSON.parse(data);
            console.log(data);
            if (responseMsg.duplicateIdError != null) {
                alert(responseMsg.duplicateIdError);
                return false;
            }
            if (responseMsg.duplicateTelError != null) {
                $('.checkTelSpan').remove();
                $('.checkTel').after("<span class='checkTelSpan' style='color:red'></span>");
                $('.checkTelSpan').text(responseMsg.duplicateTelError);
                return false;
            }
            if (responseMsg.duplicateEmailError != null) {
                $('.checkEmailSpan').remove();
                $('.checkEmail').after("<span class='checkEmailSpan' style='color:red'></span>");
                $('.checkEmailSpan').text(responseMsg.duplicateEmailError);
                return false;
            }
            if (responseMsg.failError != null) {
                alert(responseMsg.failError);
                return false;
            }
            if (responseMsg.success != null) {
                location.href = "sendEmail.do";
            }
        },
        error: function (e) {
            alert("오류가 발생했습니다.");
        }
    })
}
