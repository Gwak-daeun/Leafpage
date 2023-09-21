//프론트단 회원가입 유효성 검사
const idCheckRule = /^(?=.*?[a-z,A-Z])(?=.*?[0-9]).{6,}$/;
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
        $('.checkId').after("<span class='checkIdSpan' style='color:red'>※아이디는 영문자+숫자 조합으로 6자 이상입니다.</span>");
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
        alert("아이디는 영문자+숫자 조합으로 6자 이상입니다.");
        userId.focus();
        return false;
    }

    if(!passwordCheckRule.test(userPassword.val())) {
        alert('비밀번호는 영문자+숫자+특수문자 조합으로 8자리 이상입니다.');
        userPassword.focus();
        return false;
    }

    if(userPassword.val() !== userPasswordConfirm.val()) {
        alert('비밀번호가 일치하지 않습니다.')
        userPasswordConfirm.focus();
        return false;
    }

    if (userEmail.val() == "") {
        alert('이메일 주소를 입력하세요.');
        userEmail.focus();
        return false;
    } else if (!emailCheckRule.test(userEmail.val())) {
        alert('이메일 형식이 올바르지 않습니다.')
        userEmail.focus();
        return false;
    }

    if(userTel.val() == "") {
        alert('휴대전화번호를 입력하세요.');
        userTel.focus();
        return false;
    } else if (!phoneCheckRule.test(userTel.val())) {
        alert('휴대전화번호가 올바르지 않습니다.');
        userTel.focus();
        return false;
    }

    if (userSecurityQuestion.val() == "") {
        alert('비밀번호찾기 질문이 선택되지 않았습니다.');
        userSecurityQuestion.focus();
        return false;
    }

    if (userSecurityAnswer.val() == "") {
        alert('비밀번호 찾기 답을 입력하세요.');
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
        alert("다른 아이디를 입력해주세요.")
        userId.focus();
    }else if(status === "true"){
        console.log("수많은 난관을 뚫고 가입한 것을 축하드립니다.");
        alert("가입이 완료되었습니다. 잠시후 이메일에 인증하시면 서비스 이용이 가능합니다.")
        $("form[name=signup_form]").submit();
    }
}

