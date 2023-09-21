let inputId = $("#inputId");
let selectQuestion = $("#selectQuestion");
let inputAnswer = $("#inputAnswer");
let find_pw_btn = $("#find_pw_btn");

function checkNull() {
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
                updatePw.html("존재하지 않는 사용자입니다.");
            }
            else if(passwordChangeAccess === "0") {
                updatePw.html("인증정보가 일치하지 않습니다.");
            }
            else if(passwordChangeAccess === "1") {
                updatePw.html("비밀번호 찾기 인증이 성공하였습니다. 이제 새 비밀번호를 설정할 수 있습니다.");
            }
            updatePw.show();
        },
        error: function(e){
            alert("오류가 발생했습니다.");
        }
    });
}

