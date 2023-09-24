$(document).ready(function(){
    let msg = $('#msg').val();
    if(msg !== "") {
        console.log("index alert: "+msg);
        alert(msg);
        $.ajax({
            url: 'clearMsg.do',
            type: 'POST',
            async: true,
            success: function(response) {
                console.log("msg 세션 초기화 성공");
            },
            error: function(e) {
                console.log("msg 세션 초기화 중에 오류가 발생했습니다.");
            }
        });
    }
});