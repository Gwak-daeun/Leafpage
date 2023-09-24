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
                console.log("msg session 초기화됨");
            },
            error: function(e) {
                console.log("msg session 초기화 중에 오류발생");
            }
        });
    }
});

function logoutConfirm() {
    let confirmLogout = confirm("로그아웃 하시겠습니까?");
    if(confirmLogout) {
        location.href="logout.do";
    }
}