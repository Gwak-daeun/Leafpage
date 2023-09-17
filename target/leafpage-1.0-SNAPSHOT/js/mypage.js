$(document).ready(function () {

    $(".bottom_tab > li").click(function () {
        let idx = $(this).index();

        $(this).addClass("on").siblings().removeClass("on");

        $(".bottom_content").eq(idx).addClass("on").siblings(".bottom_content").removeClass("on");
    });

    $(".bottom_content, .return_book, .rent_book").on("mousewheel",function(e) {
        let wheelDelta = e.originalEvent.wheelDelta;
        if(wheelDelta > 0){
            $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());
        }else{
            $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());
        }
    });

    $("#closeBtn").click(function () {

        let modalY = $(".modal-body").scrollTop();

        let modalWidth = $(".modal-body").width();

        console.log("Y축: " + modalY + "너비 : " + modalWidth );

        $.ajax({
            url: "/saveUserBookY.do",
            type: "POST",
            data: {
                modalY : modalY,
                 modalWidth: modalWidth
            },
            success: function (response) {
                console.log("서버 응답: ", response);
            },
            error: function (error) {
                console.error("에러 발생: ", error);
            }
        });
    });

});