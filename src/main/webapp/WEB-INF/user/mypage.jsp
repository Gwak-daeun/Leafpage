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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/mypage.css">

</head>
<body>
<%@include file="../component/header.jsp" %>

<section class="mp_container">
    <div class="top">
        <div class="top_box">
            <h5 class="top_box_title">Guest</h5>
            <button type="button" class="btn1 btn-sm"><a href="updateMyInfoView.do">내 정보 수정</a></button>
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
                <c:if test="${book eq null}">
                    <h4 style="margin-top: 20px" class="not-book">대여중인 도서가 없습니다.</h4>
                </c:if>
                <c:if test="${book != null}">
                    <c:forEach var="book" items="${books}" begin="0" end="4" step="1" >
                        <li id="bookLi" >
                            <div onclick="openViewer(${book.rentalNo}, ${book.scrollY}, ${book.modalWidth})" class="card" >
                                <img src="${book.bookImg}" class="card-img-top" alt="..." />
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
                </c:if>


            </ul>
        </div>

        <div class="bottom_content">
            <ul class="flex return_book">
                <c:if test="${userReturnedBook eq null}">
                    <h4 style="margin-top: 20px" class="not-book">그냥 도서가 없습니다.</h4>
                </c:if>
                <c:if test="${userReturnedBook != null}">
                    <c:forEach var="userReturnedBook" items="${userReturnedBooks}">
                        <li>
                            <div class="card">
                                <img src="${userReturnedBook.bookImg}" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <h5 class="card-title">${userReturnedBook.bookName}</h5>
                                    <p class="card-author">${userReturnedBook.bookAuthorName}</p>
                                    <p class="card-period">반납일 : ${userReturnedBook.actualReturnDate}</p>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>
</section>

<!-- 제이쿼리 자바스크립트 추가하기 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="../js/mypage.js"></script>
<script type="text/javascript" src="../../js/alertMsg.js"></script>
</body>
</html>
