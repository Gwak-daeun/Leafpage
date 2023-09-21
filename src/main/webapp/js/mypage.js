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



    // $("#closeBtn").click(function () {
    //
    //     let modalY = $(".modal-body").scrollTop();
    //
    //     let modalWidth = $(".modal-body").width();
    //
    //     let rentalNo =
    //
    //     console.log("Y축: " + modalY + "너비 : " + modalWidth);
    //
    //     $.ajax({
    //         url: "/saveUserBookY.do",
    //         type: "POST",
    //         data: {
    //             modalY : modalY,
    //             modalWidth: modalWidth,
    //             rentalNo : rentalNo
    //         },
    //         success: function (response) {
    //             // console.log("서버 응답: ", response);
    //             location.reload();
    //         },
    //         error: function (error) {
    //             console.error("에러 발생: ", error);
    //         }
    //     });
    // });


    $(".select-mode").click(function(){
        $(".modal-content").toggleClass("dark-mode");

        if ($(".modal-content").hasClass("dark-mode")) {
            // 다크 모드일 때는 다크 모드 아이콘을 보이도록 설정
            $(".dark-icon").css("display", "none");
            $(".light-icon").css("display", "block");
            $(".modal-content").css("border-color", "#000000");
        } else {
            // 라이트 모드일 때는 라이트 모드 아이콘을 보이도록 설정
            $(".dark-icon").css("display", "block");
            $(".light-icon").css("display", "none");
            $(".modal-content").css("border-color", "#ffffff");


        }
    });

});