
//클릭으로 탭 메뉴 변경
$(document).ready(function () {
  $(".tab-button > li").click(function () {
    console.log("dhfsldkjfs");
    var idx = $(this).index();

    $(this).addClass("on").siblings().removeClass("on");

    $(".tabmenu .tab-content")
      .eq(idx)
      .addClass("on")
      .siblings(".tab-content")
      .removeClass("on");
  })
});

// $("#emptyH").show();
// $("#fullH").hide();

//하트 채워지고 비워지는 기능
function likeCheck() {

    /*웹페이지 열었을 때*/
    $.ajax({
        url: "LikeHeart.do",
        type: 'POST',
        async: true,
        dataType: 'text',
        data: {
            userNo: '9',
            isbn: '040501813-4',
        },
        success: function (data) {
            console.log(data);

            // $("#emptyH").show();
            // $("#fullH").hide();
            if (data == "1") {
                $("#emptyH").hide();
                $("#fullH").show();
                console.log("하트 추가");
            } else if (data == "0") {
                $("#emptyH").show();
                $("#fullH").hide();
                console.log("하트 취소");
            }
            // $("#heartCount").text(heartCount);
            // location.reload();
        },
        error: function () {
            console.log("서버에서 상태를 가져오는 데 실패했습니다.");
        }
    });

    /*emptyH을 클릭했을 때 fullH를 보여줌*/
    // $("#emptyH").click(function () {
    //     $("#emptyH").hide();
    //     $("#fullH").show();
    // });
    //
    // /*fullH를 클릭했을 때 emptyH을 보여줌*/
    // $("#fullH").click(function () {
    //     $("#emptyH").show();
    //     $("#fullH").hide();
    // });
}



//별점 표시
$('.starRev span').click(function(){
  $(this).parent().children('span').removeClass('on');
  $(this).addClass('on').prevAll('span').addClass('on');
  return false;
});