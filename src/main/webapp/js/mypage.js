$(document).ready(function () {
  $(".bottom_tab > li").click(function () {
    let idx = $(this).index();

    $(this).addClass("on").siblings().removeClass("on");

    $(".bottom_content")
      .eq(idx)
      .addClass("on")
      .siblings(".bottom_content")
      .removeClass("on");
  });

  $(".bottom_content, .return_book, .rent_book").on("mousewheel", function (e) {
    let wheelDelta = e.originalEvent.wheelDelta;
    if (wheelDelta > 0) {
      $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());
    } else {
      $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());
    }
  });

  $(".modal").on("shown.bs.modal", function () {
    let modalWidth = $(".modal-body").width();

    if (dbViewerWidth > modalWidth) {
      // 컴으로 보다가 모바일로 보려할 때
      $(this)
        .find(".modal-body")
        .scrollTop((viewerY * 666) / 321);
    }
    if (dbViewerWidth < modalWidth) {
      // 모바일로 보다가 컴으로 보려할 때
      $(this)
        .find(".modal-body")
        .scrollTop((viewerY * 321) / 666);
    }
    if (dbViewerWidth === modalWidth || viewerY === 0) {
      // 이전에 보던 기기랑 같은 기기로 볼 때
      $(this).find(".modal-body").scrollTop(viewerY);
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


  $(".select-mode").click(function () {
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

function returnCheck(bookName, rentalNo) {
  if (window.confirm(bookName + "을(를) 정말 반납하시겠습니까?")) {
    returnBook(bookName, rentalNo);
  } else {
    alert("반납을 취소했습니다.")
  }
}

function returnBook(bookName, rentalNo) {
  $.ajax({
    url: "returnBook.do",
    type: "post",
    dataType: "text",
    contentType: "application/x-www-form-urlencoded",
    async: true,
    data: { rentalNo: rentalNo },

    success: function (data) {
      console.log(data);
      if (data === "ReturnFail") {
        alert("반납에 실패했습니다.");
      } else {
        alert(`${bookName}이(가) 반납 완료했습니다.`)
        location.reload();
      }
    },
    error: function (xhr, textStatus, errorThrown) {
      // 에러 처리 코드
      console.log("Error: " + textStatus);
    },
  });
}
