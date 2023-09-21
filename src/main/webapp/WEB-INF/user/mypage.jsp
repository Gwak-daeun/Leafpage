<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../css/mypage.css">

</head>
<body>
<%@include file="../component/header.jsp" %>

<section class="mp_container">
    <div class="top">
        <div class="top_box">
            <h5 class="top_box_title">Guest</h5>
            <button type="button" class="btn1 btn-sm"><a href="myInfoView.do">내 정보 수정</a></button>
        </div>
        <div class="top_box">
            <h5 class="top_box_title">현재 대여 권수</h5>
            <p class="top_box_content">${books.size()}/5</p>
        </div>
        <div class="top_box">
            <h5 class="top_box_title">누적 대여 권수</h5>
            <p class="top_box_content">${books.size() + userReturnedBooks.size()}</p>
        </div>
    </div>

    <div class="bottom">
        <ul class="flex bottom_tab">
            <li class="on"><a href="#">대여중 도서</a></li>
            <li><a href="#">반납한 도서</a></li>
        </ul>

        <div class="bottom_content on">
            <ul class="flex rent_book">
                <c:forEach var="book" items="${books}" begin="0" end="4" step="1" >
                    <li id="bookLi" >
                        <div onclick="openViewer(${book.rentalNo}, ${book.scrollY}, ${book.modalWidth})" class="card" >
                            <img src="image/마주.png" class="card-img-top" alt="..." />
                            <c:if test="${book.modalWidth eq 321}">
                                <img class="device-icon" src="../css/icons/phone_iphone.png" />
                            </c:if>
                            <c:if test="${book.modalWidth ne 321}">
                                <img class="device-icon" src="../css/icons/desktop_windows.png" />
                            </c:if>
                            <div class="card-body">
                                <h5 class="card-title">${book.bookName}</h5>
                                <p class="card-author">${book.bookAuthorName}</p>
                                <p class="card-period">${book.rentalDate} ~ ${book.scheduledReturnDate}</p>
                            </div>
                        </div>
                        <button type="button" class="btn1 btn-sm"
                                onclick="returnCheck(`${book.bookName}`, ${book.rentalNo})">
                            반납하기
                        </button>
                    </li>

                    <%--책 뷰어--%>
                    <%@include file="../component/bookModal.jsp"%>


                </c:forEach>

            </ul>
        </div>

        <div class="bottom_content">
            <ul class="flex return_book">
                <c:forEach var="userReturnedBook" items="${userReturnedBooks}">
                    <li>
                        <div class="card">
                            <img src="image/마주.png" class="card-img-top" alt="..." />
                            <div class="card-body">
                                <h5 class="card-title">${userReturnedBook.bookName}</h5>
                                <p class="card-author">${userReturnedBook.bookAuthorName}</p>
                                <p class="card-period">반납일 : ${userReturnedBook.actualReturnDate}</p>
                            </div>
                        </div>
                    </li>
                </c:forEach>

            </ul>
        </div>
    </div>
</section>

<script>
    function openViewer(rentalNo, dbScrollY, dbModalWidth) {

        $(`#` + rentalNo).modal('show');


        $(`#` + rentalNo).on('shown.bs.modal', function () {

            let modalBody = $(`#` + rentalNo).find(".modal-body"); // 해당 모달 창의 .modal-body 요소 선택

            let truncatedWidth = Math.floor(modalBody.width());

            console.log("현재 모달 폭 : ", truncatedWidth);

            if (dbModalWidth === truncatedWidth || dbScrollY === 0) {
                modalBody.scrollTop(dbScrollY);
                console.log("동일한 디바이스 작동 : ", dbModalWidth);
            }
            if (dbModalWidth > truncatedWidth) {
                modalBody.scrollTop((dbScrollY * 666) / 321 );
                console.log("컴에서 모바일로 디바이스 변경 : ", dbModalWidth);
            }
            if (dbModalWidth < truncatedWidth) {
                modalBody.scrollTop((dbScrollY * 321) / 666 );
                console.log("모바일에서 컴으로 디바이스 변경 : ", dbModalWidth);
            }
        });
    }


    function sendY(rentalNo) {

        let modalY = 0;

        let modalWidth = 0;

        let modalBody = $(`#` + rentalNo).find(".modal-body");

        modalY = modalBody.scrollTop();

        modalWidth = Math.floor(modalBody.width());

        console.log("Y축: " + modalY + "너비 : " + modalWidth, ", 유저 넘버 : ", rentalNo);

        $.ajax({
            url: "/saveUserBookY.do",
            type: "POST",
            data: {
                modalY : modalY,
                modalWidth: modalWidth,
                rentalNo : rentalNo
            },
            success: function (response) {
                // console.log("서버 응답: ", response);
                location.reload();
            },
            error: function (error) {
                console.error("에러 발생: ", error);
            }
        });
    }
</script>

<!-- 제이쿼리 자바스크립트 추가하기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="../js/mypage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>