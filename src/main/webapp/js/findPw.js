const passwordCheckRule = /^(?=.*?[a-z,A-Z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;

function checkNullPw() {
    let inputId = $("#inputId");
    let selectQuestion = $("#selectQuestion");
    let inputAnswer = $("#inputAnswer");
    let find_pw_btn = $("#find_pw_btn");

    if(inputId.val() === '' || inputId.val() === null) {
        $(".checkSpan").remove();
        find_pw_btn.before("<span class='checkSpan' style='color:red'>※아이디를 입력해주세요.</span>")
        return false;
    }
    if(selectQuestion.val() === '' || selectQuestion.val() === null) {
        $(".checkSpan").remove();
        find_pw_btn.before("<span class='checkSpan' style='color:red'>※비밀번호 찾기 질문을 선택해주세요.</span>")
        return false;
    }
    if(inputAnswer.val() === '' || inputAnswer.val() === null) {
        $(".checkSpan").remove();
        find_pw_btn.before("<span class='checkSpan' style='color:red'>※비밀번호 찾기 답을 입력해주세요.</span>")
        return false;
    }

    if (selectType.val() == "findByEmail") {
        if (inputEmail.val() == '' || inputEmail.val() == null) {
            $(".checkSpan").remove();
            find_pw_btn.before("<span class='checkSpan' style='color:red'>※이메일을 입력해주세요.</span>")
            return false;
        } else {
            $(".checkSpan").remove();
            console.log("이메일로 검사");
            findPw(inputId.val(), inputEmail.val(), null, selectQuestion.val(), inputAnswer.val());
        }
    }

    if (selectType.val() == "findByTel") {
        if (inputTel.val() == '' || inputTel.val() == null) {
            $(".checkSpan").remove();
            find_pw_btn.before("<span class='checkSpan' style='color:red'>※전화번호를 입력해주세요.</span>")
            return false;
        } else {
            $(".checkSpan").remove();
            console.log("전화번호로 검사");
            findPw(inputId.val(),null, inputTel.val(), selectQuestion.val(), inputAnswer.val());
        }
    }
}

function findPw(inputIdValue, inputEmailValue, inputTelValue, selectQuestionValue, inputAnswerValue) {
    $.ajax({
        url: 'findPw.do',
        type: 'POST',
        async: true,
        data: {
            inputId: inputIdValue,
            inputEmail: inputEmailValue,
            inputTel: inputTelValue,
            selectQuestion: selectQuestionValue,
            inputAnswer: inputAnswerValue
        },
        dataType: 'text',
        success: function(passwordChangeAccess){
            //(1)통과
            //(0)조회는 되는데 질문,대답이 틀림
            //(-1)조회되지 않는 사용자
            if(passwordChangeAccess === "-1") {
                updatePw.html("입력하신 정보를 다시 한 번 확인해 주세요.");
            }
            else if(passwordChangeAccess === "0") {
                updatePw.html("인증정보가 일치하지 않습니다.");
            }
            else {
                updatePw.html("비밀번호 찾기 인증이 성공하였습니다. 이제 새 비밀번호를 설정할 수 있습니다.");
                updatePw.append(passwordChangeAccess);
            }
            updatePw.show();
        },
        error: function(e){
            alert("오류가 발생했습니다.");
        }
    });
}

function newPasswordCheck() {
    let newPassword = $('#newPassword');
    let confirmNewPassword = $('#confirmNewPassword');
    let checkSpan = $('#checkSpan');

    if(newPassword.val() === '' || confirmNewPassword.val() === '') {
        console.log("※입력되지 않은 사항이 있습니다.");
        checkSpan.html("<span class='checkIdSpan' style='color:red'>※입력되지 않은 사항이 있습니다.</span>");
        return false;
    }
     else if(!passwordCheckRule.test(newPassword.val())) {
        console.log("※비밀번호는 영문자+숫자+특수문자 조합으로 8자리 이상입니다.");
        checkSpan.html("<span class='checkIdSpan' style='color:red'>※비밀번호는 영문자+숫자+특수문자 조합으로 8자리 이상입니다.</span>");
        newPassword.focus();
        return false;
    }
    else if(newPassword.val() !== confirmNewPassword.val()) {
        console.log(newPassword.val());
        console.log(confirmNewPassword.val());
        console.log("※비밀번호가 일치하지 않습니다.");
        checkSpan.html("<span class='checkIdSpan' style='color:red'>※비밀번호가 일치하지 않습니다.</span>");
        confirmNewPassword.focus();
        return false;
    }
    else {
        console.log("※새 비밀번호로 변경 중입니다.");
        $("form[name=new_pw_form]").submit();
        alert("비밀번호가 변경되었습니다.");
    }
}