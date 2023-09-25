<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/detailPage.css">
    <script src="https://kit.fontawesome.com/1db70bd877.js" crossorigin="anonymous"></script>
</head>
<body>
<%@include file="../component/header.jsp" %>

<section>
    <c:set var="errorMsg" value="${errorMsg}"/>
    <c:set var="failed" value="${failed}"/>
    <c:set var="isbn" value="${bookDetail.ISBN}"/>
    <div class="inline">
        <div class="mg big-book">
            <img src="${bookDetail.bookImg}" class="imgstyle">
            <div class="center">
                <span class="in-block"><h3>${bookDetail.bookName}</h3></span>
                <span>${bookDetail.bookAuthorName}</span>
                <div class="small">${bookDetail.categories} | ${bookDetail.bookPublisherName} |
                    발행일: ${bookDetail.bookPubDate}</div>
                <div class="bottom-mg inline">
                    <button class="btn rental" onclick="rent(${bookDetail.ISBN})">대여하기</button>
                    <%--                <button class="btn preview">미리보기</button>   &lt;%&ndash; 로그인 해야 볼 수 있음&ndash;%&gt;--%>

                    <c:if test="${heartSelect == 1}">
                        <img id="fullH" style="width: 20px; height: 20px; margin-left: 5px;"
                             src="../../css/icons/full.png"
                             onclick="likeCheck(`${bookDetail.ISBN}`)">
                    </c:if>
                    <c:if test="${heartSelect == 0}">
                        <img id="emptyH" style="width: 20px; height: 20px; margin-left: 5px; margin-bottom: 5px"
                             src="<c:url value="/css/icons/empty.png"/>" onclick="likeCheck(`${bookDetail.ISBN}`)">
                    </c:if>
                    <span style="margin-top: 8px">${ heartCount }</span>

                </div>
            </div>
        </div>
        <div class="mg container">
            <div class="tabmenu">
                <ul class="tab-button">
                    <li class="on"><a href="#">책 소개</a></li>
                    <li><a href="#">목차 소개</a></li>
                    <li><a href="#">리뷰</a></li>
                    <li><a href="#">저자의 책</a></li>
                </ul>
                <div class="tab-content on">
                    <div class="contexth">
                        ${bookDetail.bookInfo}
                    </div>
                </div>
                <div class="tab-content">
                    <div class="contexth">
                        ${bookDetail.bookChapter}
                    </div>
                </div>
                <div class="tab-content">
                    <c:if test="${userNo == null || userEmailChecked eq false}">
                        <div class="enroll-right">
                            <button onclick="openReview()" class="btn rental ">등록하기</button>
                        </div>
                    </c:if>
                    <c:if test="${userNo != null && userEmailChecked eq true}">
                        <div class="enroll-right">
                            <button onclick="openReview()" class="btn rental" data-toggle="modal" href="#reviewenroll">등록하기</button>
                        </div>
                    </c:if>

                    <ul class="review-content">
                        <c:if test="${reviews.size() eq 0}">
                            <h4 style="margin-top: 50px">아직 댓글을 작성한 사람이 없습니다.</h4>
                        </c:if>
                        <c:if test="${reviews.size() > 0}">
                            <c:forEach var="review" items="${reviews}">
                                <li>
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="row">
                                                <div class="card-title">
                                                <span>
                                                    <c:if test="${review.reviewRating eq 5}">
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                    </c:if>
                                                    <c:if test="${review.reviewRating eq 4}">
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                    </c:if>
                                                    <c:if test="${review.reviewRating eq 3}">
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                    </c:if>
                                                    <c:if test="${review.reviewRating eq 2}">
                                                        <span class="star">⭐</span>
                                                        <span class="star">⭐</span>
                                                    </c:if>
                                                    <c:if test="${review.reviewRating eq 1}">
                                                        <span class="star">⭐</span>
                                                    </c:if>

                                                </span>
                                                    <span class="review-top-right">
                                                    <p>작성일&nbsp; ${review.reviewDate}</p>
                                                </span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--강의명 밑 내용과 추천-->
                                        <div class="card-body">
                                            <p class="card-text">
                                                    ${review.reviewContent}
                                            </p>
                                            <div class="row">
                                                <div class="col-9 text-left">
                                                </div>

                                                <div class="col-3 text-right">
                                                    <c:set var="userNoStr" value="${userNo}"/>
                                                    <c:set var="reviewNoStr" value="${review.userNo}"/>
                                                    <c:if test="${userNoStr eq reviewNoStr}">
                                                        <a onclick="return confirm('삭제하시겠습니까?')"
                                                           href="/removeReview.do?reviewNo=${review.reviewNo}&isbn=${bookDetail.ISBN}"
                                                        >삭제
                                                        </a>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
                <div class="tab-content">
                    <ul class="contexth">
                        <c:forEach var="sameAuthorBook" items="${sameAuthorBooks}">
                            <li data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                <a href="/detailPageView.do?isbn=${sameAuthorBook.ISBN}">
                                    <div class="book-list">
                                        <img class="book-cover" src="${sameAuthorBook.bookImg}"/>
                                        <div class="book-title">${sameAuthorBook.bookName}</div>
                                        <div class="book-author"> ${sameAuthorBook.bookPublisherName}</div>
                                    </div>
                                </a>
                            </li>

                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
</section>

<div class="modal fade show" id="rental" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"> <!--모달 안에 들어가 있는 내용 정의-->
            <div class="modal-body">
                <div class="form-group">
                    <h3 class="modal-title">대여에 성공했습니다!</h3>
                    <p class="scheduled-return-date"></p>
                </div>

                <div class="form-footer">
                    <button class="btn close" onclick="closeModal('#rental')">돌아가기</button>
                    <button class="btn move-page" onclick="location.href='mypageInfo.do'">마이페이지로 이동</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade show" id="reviewenroll" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">리뷰 작성</h5>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label>평점</label>
                    <div class="starRev">
                        <!-- 편의 상 가장 첫번째의 별은 기본으로 class="on"이 되게 설정해주었습니다. -->
                        <span class="starR on">⭐</span>
                        <span class="starR">⭐</span>
                        <span class="starR">⭐</span>
                        <span class="starR">⭐</span>
                        <span class="starR">⭐</span>
                    </div>

                </div>
                <div class="form-group">
                    <label>내용</label>
                    <textarea name="reviewContent" class="form-control" maxlength="2048"
                              style="height: 180px"></textarea>
                </div>
                <div class="form-footer">
                    <button id="reviewClose" type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button id="reviewRegister" class="btn rental">등록하기</button>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="modal fade show" id="required-login" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"> <!--모달 안에 들어가 있는 내용 정의-->
            <div class="modal-body">
                <div class="form-group">
                    <h3 class="modal-title">로그인 후 이용가능합니다.</h3>
                    <p>로그인 페이지로 이동하시겠습니까?</p>
                </div>
                <div class="form-footer">
                    <button class="btn close" onclick="closeModal('#required-login')">돌아가기</button>
                    <button class="btn move-page" onclick="location.href='loginView.do'">로그인페이지로 이동</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade show" id="required-auth" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"> <!--모달 안에 들어가 있는 내용 정의-->
            <div class="modal-body">
                <div class="form-group">
                    <h3 class="modal-title">이메일 인증 후 이용가능합니다.</h3>
                    <p>이메일 인증 하시겠습니까?</p>
                </div>
                <div class="form-footer">
                    <button class="btn close" onclick="closeModal('#required-auth')">돌아가기</button>
                    <button class="btn move-page" onclick="location.href='emailResendView.do'">인증 페이지로 이동</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    let errorMsg = "${errorMsg}";
    let failed = "${failed}";
    const isbn = "${isbn}";
    const userNo = "${userNo}";
    const userEmailChecked = "${userEmailChecked}";
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
<script src="../../js/detailPage.js"></script>
<script src="../../js/alertMsg.js"></script>
</body>
</html>