
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

//하트 채워지고 비워지는 기능
$(document).ready(function(){
  /*웹페이지 열었을 때*/
  $("#emptyH").show();
  $("#fullH").hide();

  /*emptyH을 클릭했을 때 fullH를 보여줌*/
  $("#emptyH").click(function(){
      $("#emptyH").hide();
      $("#fullH").show();
  });

  /*fullH를 클릭했을 때 emptyH을 보여줌*/
  $("#fullH").click(function(){
      $("#emptyH").show();
      $("#fullH").hide();
  });
});

//

//별점 표시
$('.starRev span').click(function(){
  $(this).parent().children('span').removeClass('on');
  $(this).addClass('on').prevAll('span').addClass('on');
  return false;
});

// 도서 대여
function rent(ISBN) {

    $.ajax({
        url: 'rentBook.do',
        type: 'post',
        dataType: 'text',
        contentType: "application/x-www-form-urlencoded",
        async: true,
        data: {ISBN: ISBN},

        success: function (data) {
            console.log(data);
            if (data === "overRentCount") {
                alert("회원의 도서 대여 수가 5권입니다. 더 이상 대여하실 수 없습니다.");
            } else if (data === "renting") {
                alert("현재 대여 중인 도서입니다.");
            } else {
                $('#rental').modal('show');
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            // 에러 처리 코드
            console.log('Error: ' + textStatus);
        }
    });
}


$('.rental').click(function () {
    let now = new Date();
    let scheduledReturnDate = new Date(now.setDate(now.getDate() + 7));

    let year = scheduledReturnDate.getFullYear();
    let month = scheduledReturnDate.getMonth() + 1;
    let date = scheduledReturnDate.getDate();

    $('.scheduled-return-date').text(`대여기한 : ${year}-${month}-${date}`);
});