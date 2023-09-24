let selectType = $("#selectType");
let inputEmail = $("#inputEmail");
let inputTel = $("#inputTel");
let foundId = $("#found_id");
let updatePw = $("#update_pw");

$(function(){
    foundId.hide();
    updatePw.hide();
    $('#phone').hide();  //전화번호 hide상태

    selectType.change(function() {  //selectType이 변하면
        foundId.hide();
        if(selectType.val() == "findByTel") {  //selectType의 value가 "findByTel"이면 전화번호 인풋 나옴
            $("#email").hide();
            $("#phone").show();
            $(".checkSpan").remove();
            inputEmail.val('');
        } else if (selectType.val() == "findByEmail") {  //selectType의 value가 "findByEmail"이면 이메일 인풋 나옴
            $("#phone").hide();
            $("#email").show();
            $(".checkSpan").remove();
            inputTel.val('');
        }
    })
});