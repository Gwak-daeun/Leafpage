let find_id_btn = $("#find_id_btn");
let checkText = $(".checkText");

function checkNullId() {
    foundId.hide();
    if (selectType.val() == "findByEmail") {
        if (inputEmail.val() == '' || inputEmail.val() == null) {
            checkText.text("※이메일을 입력해주세요.");
        } else {
            checkText.text("");
            findId(inputEmail.val(), null);
        }
    }

    if (selectType.val() == "findByTel") {
        if (inputTel.val() == '' || inputTel.val() == null) {
            checkText.text("※전화번호를 입력해주세요.");
        } else {
            checkText.text("");
            findId(null, inputTel.val());
        }
    }
}

function findId(inputEmailValue, inputTelValue) {
    $.ajax({
        url: 'findId.do',
        type: 'POST',
        async: true,
        data: {
            inputEmail: inputEmailValue,
            inputTel: inputTelValue,
        },
        dataType: 'text',
        success: function(data){
            let userData = JSON.parse(data);
            console.log(data);
            console.log("["+userData.foundUserId+"]")
            if(userData != null && userData.foundUserId != null){
                console.log(userData.foundUserId);
                foundId.text("아이디를 찾았습니다. 찾은 아이디는 ["+userData.foundUserId+"] 입니다.");
            } else if (userData == null || userData.foundUserId == null) {
                foundId.text("가입된 아이디가 없습니다.");
            }
            foundId.show();
        },
        error: function(e){
            location.href='findIdView.do';
        }
    });
}

