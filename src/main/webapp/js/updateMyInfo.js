let checkPassword = $('#checkPassword');
let checkSpanForWithdrawal = $('#checkSpanForWithdrawal');

function withdrawal() {
    $.ajax({
        url: 'withdrawal.do',
        type: 'POST',
        async: true,
        data: {
            checkPassword:checkPassword.val()
        },
        dataType: 'text',
        success: function(data){
            if(data === "0") {
                checkSpanForWithdrawal.html("입력하신 정보가 맞지 않습니다.");
            }
            else if(data === "1") {
                location.href = "index";
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

