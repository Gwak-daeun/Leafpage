let find_id_btn = $("#find_id_btn");

function checkNullId() {
    foundId.hide();
    if (selectType.val() == "findByEmail") {
        if (inputEmail.val() == '' || inputEmail.val() == null) {
            $(".checkSpan").remove();
            find_id_btn("<span class='checkSpan' style='color:red'>※이메일을 입력해주세요.</span>")
        } else {
            $(".checkSpan").remove();
            console.log("이메일로 검사");
            findId(inputEmail.val(), null);
        }
    }

    if (selectType.val() == "findByTel") {
        if (inputTel.val() == '' || inputTel.val() == null) {
            $(".checkSpan").remove();
            find_id_btn("<span class='checkSpan' style='color:red'>※전화번호를 입력해주세요.</span>")
        } else {
            $(".checkSpan").remove();
            console.log("전화번호로 검사");
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
                foundId.html("아이디를 찾았습니다. 찾은 아이디는 ["+userData.foundUserId+"] 입니다.");
            } else if (userData == null || userData.foundUserId == null) {
                foundId.html("가입된 아이디가 없습니다.");
            }
            foundId.show();
        },
        error: function(e){
            location.href='findIdView.do';
        }
    });
}

