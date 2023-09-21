
//클릭으로 탭 메뉴 변경
$(document).ready(function () {

    if (errorMsg == "리뷰 삭제에 실패했어요.") {
        alert(errorMsg);
    }
    if (failed == "리뷰 등록에 실패했어요.") {
        alert(failed);
    }

//하트 채워지고 비워지는 기능
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


$(".tab-button > li").click(function () {
    var idx = $(this).index();

    $(this).addClass("on").siblings().removeClass("on");

    $(".tabmenu .tab-content")
        .eq(idx)
        .addClass("on")
        .siblings(".tab-content")
        .removeClass("on");
});


//별점 표시
$('.starRev span').click(function(){
    $(this).parent().children('span').removeClass('on');
    $(this).addClass('on').prevAll('span').addClass('on');
});

$('.stars .fa').click(function () {
    $(this).addClass('active')
    $(this).prevAll().addClass('active')
    $(this).nextAll().removeClass('active')

    let num = $(this).index()
    let starRate = num + 1

});

$("#reviewRegister").click(function () {

    var reviewContent = $('textarea[name="reviewContent"]').val();

    var selectedRating = $('.starRev span.on').length;

    console.log("리뷰 내용 : ", reviewContent, ", 별점 : ", selectedRating);

    $.ajax({
        type: 'POST',
        url: '/makeReview.do',
        data: {
            rating: selectedRating,
            content: reviewContent
        },
        success: function(response) {
            alert('리뷰 등록에 성공했어요.');
            location.reload();
        },
        error: function(error) {
            alert('리뷰 등록에 실패했어요.' + error);
        }
    });

});

    // $(".delete-button").click(function () {
    //
    //     console.log("삭제 시작");
    //     console.log("리뷰 번호 확인: " + reviewNo);
    //
    //     if (window.confirm("이 리뷰를 삭제하시겠어요?")) {
    //         $.ajax({
    //             type: "POST",
    //             url: '/removeReview.do',
    //             data: {
    //                 reviewNo : reviewNo
    //             },
    //             success: function (response) {
    //                 alert('리뷰 삭제 완료');
    //                 location.reload();
    //             },
    //             error: function (error) {
    //                 alert('리뷰 삭제 실패 - ' + error.message);
    //             }
    //         });
    //     }
    // });

$("#reviewClose").click(function () {

    $('textarea[name="reviewContent"]').val('');
    $('.starRev span.on').removeClass('on');

});

});