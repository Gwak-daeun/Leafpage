
//클릭으로 탭 메뉴 변경
$(document).ready(function () {
  $(".tab-button > li").click(function (e) {
      e.preventDefault();
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
            userNo: '1',
            isbn: '040501813854',
        },
        success: function (data) {
            console.log(data);

            location.reload();
        },
        error: function () {
            console.log("서버에서 상태를 가져오는 데 실패했습니다.");
        }
    });

}



//별점 표시
$('.starRev span').click(function(){
  $(this).parent().children('span').removeClass('on');
  $(this).addClass('on').prevAll('span').addClass('on');
});