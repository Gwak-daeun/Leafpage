const passwordCheckRule = /^(?=.*?[a-z,A-Z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;

function checkNullPw() {
  let inputId = $("#inputId");
  let selectQuestion = $("#selectQuestion");
  let inputAnswer = $("#inputAnswer");
  let checkText = $(".checkText");

  if (inputId.val() === "" || inputId.val() === null) {
    checkText.text("※아이디를 입력해주세요.");
    return false;
  }
  if (selectQuestion.val() === "" || selectQuestion.val() === null) {
    checkText.text("※비밀번호 찾기 질문을 선택해주세요.");
    return false;
  }
  if (inputAnswer.val() === "" || inputAnswer.val() === null) {
    checkText.text("※비밀번호 찾기 답을 입력해주세요.");
    return false;
  }

  if (selectType.val() == "findByEmail") {
    if (inputEmail.val() == "" || inputEmail.val() == null) {
      checkText.text("※이메일을 입력해주세요.");
      return false;
    } else {
      checkText.text("");
      findPw(inputId.val(), inputEmail.val(), null, selectQuestion.val(), inputAnswer.val());
    }
  }

  if (selectType.val() == "findByTel") {
    if (inputTel.val() == "" || inputTel.val() == null) {
      checkText.text("※전화번호를 입력해주세요.");
      return false;
    } else {
      checkText.text("");
      findPw(inputId.val(), null, inputTel.val(), selectQuestion.val(), inputAnswer.val());
    }
  }
}

function findPw(inputIdValue, inputEmailValue, inputTelValue, selectQuestionValue, inputAnswerValue) {
  let updatePw = $("#update_pw");
  $.ajax({
    url: "findPw.do",
    type: "POST",
    async: true,
    data: {
      inputId: inputIdValue,
      inputEmail: inputEmailValue,
      inputTel: inputTelValue,
      selectQuestion: selectQuestionValue,
      inputAnswer: inputAnswerValue,
    },
    dataType: "text",
    success: function (passwordChangeAccess) {
      //(1)통과
      //(0)조회는 되는데 질문,대답이 틀림
      //(-1)조회되지 않는 사용자
      if (passwordChangeAccess === "-1") {
        updatePw.html("입력하신 정보를 다시 한 번 확인해 주세요.");
      } else if (passwordChangeAccess === "0") {
        updatePw.html("인증정보가 일치하지 않습니다.");
      } else {
        updatePw.html("비밀번호 찾기 인증이 성공하였습니다. 이제 새 비밀번호를 설정할 수 있습니다.");
        updatePw.append(passwordChangeAccess);
      }
      updatePw.show();
    },
    error: function (e) {
      alert("오류가 발생했습니다.");
    },
  });
}

function newPasswordCheck() {
  let newPassword = $("#newPassword");
  let confirmNewPassword = $("#confirmNewPassword");
  let checkPasswordText = $(".checkPasswordText");

  if (newPassword.val() === "" || confirmNewPassword.val() === "") {
    checkPasswordText.text("※입력되지 않은 사항이 있습니다.");
    console.log("※입력되지 않은 사항이 있습니다.");
    return false;
  } else if (!passwordCheckRule.test(newPassword.val())) {
    checkPasswordText.text("※비밀번호는 영문자+숫자+특수문자 조합으로 8자리 이상입니다.");
    console.log("※비밀번호는 영문자+숫자+특수문자 조합으로 8자리 이상입니다.");
    newPassword.focus();
    return false;
  } else if (newPassword.val() !== confirmNewPassword.val()) {
    console.log(newPassword.val());
    console.log(confirmNewPassword.val());
    console.log("※비밀번호가 일치하지 않습니다.");
    checkPasswordText.text("※비밀번호가 일치하지 않습니다.");
    confirmNewPassword.focus();
    return false;
  } else {
    console.log("※새 비밀번호로 변경 중입니다.");
    $("form[name=new_pw_form]").submit();
  }
}
