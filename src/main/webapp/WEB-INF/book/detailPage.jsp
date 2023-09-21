<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/detailPage.css">
    <meta name="viewport" content="width=device-width">
    <script src="https://kit.fontawesome.com/1db70bd877.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
    <nav class="navbar index-nav">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1"><img src="css/icons/nest_eco_leaf.png" /> </span>
            <span></span>
            <span>
                <button type="button" class=" btn2 btn-sm">회원가입</button>
                <button type="button" class=" btn1 btn-sm">로그인</button>
            </span>
        </div>
    </nav>
</header>
<section>
    <c:set var="errorMsg" value="${errorMsg}"/>
    <c:set var="failed" value="${failed}"/>
    <div>
        <div class="mg">
            <img src="image/bookcover.png" class="imgstyle">
            <div class="center">
                <span class="inline">${bookDetail.bookName}</span>
                <span >${bookDetail.bookAuthorName}</span>
                <div>${bookDetail.categories} | ${bookDetail.bookPublisherName} | 발행일: ${bookDetail.bookPubDate}</div>
                <div class="bottom-mg inline">
                    <button class="btn rental" onclick="rent(${bookDetail.ISBN})">대여하기</button>
                    <button class="btn preview">미리보기</button>   <%-- 로그인 해야 볼 수 있음--%>

                    <img id="emptyH" style="width: 20px; margin-left: 5px;" src="/css/icons/empty.png" onclick="location.href='LikeHeart.do'">
                    <img id="fullH" style="width: 20px; margin-left: 5px;" src="/css/icons/full.png" onclick="location.href='LikeHeart.do'">
                    ${ heartCount }
<%--                    <i class="fa-solid fa-heart fa-xl" style="color: #d7443e;"></i>--%>
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
                <div>
                   ${bookDetail.bookChapter}
                </div>
            </div>
            <div class="tab-content">
                <div class="enroll-right">
                    <button class="btn rental " data-toggle="modal" href="#reviewenroll">등록하기</button>
                </div>
                <ul class="review-content">
                    <c:forEach var="review" items="${reviews}">
                        <li>
                            <div class="card">

                                <div class="card-header">
                                    <div class="row">
                                        <div class="card-title">
                                    <span>
                                        ${review.reviewRating}
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
<%--                                            <c:set var="reviewNo" value="${review.review_no}"/>--%>
                                            <a onclick="return confirm('삭제하시겠습니까?')" href="/removeReview.do?reviewNo=${review.reviewNo}">삭제</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                
                
                <div>
                    <!-- 기대가 많이 됩니다
                    내용 평점5점   편집/디자인 평점5점 | f******n | 2023-08-28
                    원문주소 : https://blog.yes24.com/document/18479433

                    블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다 -->
                </div>
            </div>
            <div class="tab-content">
                <div>리더의 용기, 성공의 문을 여는 마스터키...</div>
            </div>
        </div>
    </div>
</section>

<!-- <div class="modal fade show" id="rental" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div>
                    <h3 class="modal-title" id="modal">대여에 성공했습니다!</h3>
                    <p>대여기간 2023-09-28</p>
                </div>

                <div>
                    <button class="btn rental">바로보기</button>
                    <button class="btn preview"><a href="">도서목록</a></button>
                </div>
            </div>
        </div>
    </div>
</div> -->
<div class="modal fade show" id="rental" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"> <!--모달 안에 들어가 있는 내용 정의-->
        <div class="modal-body">
            <div class="form-group">
                <h3 class="modal-title" id="modal">대여에 성공했습니다!</h3>
                <p class="scheduled-return-date"></p>
            </div>

            <div class="form-footer">
                <button class="btn rental">바로보기</button>
                <button class="btn preview"><a href="">도서목록</a></button>
            </div>
        </div>
      </div>
    </div>
</div>

<div class="modal fade show" id="reviewenroll" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal">리뷰 작성</h5>
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
                    <textarea name="reviewContent" class="form-control" maxlength="2048" style="height: 180px"></textarea>
                    </div>
                  <div class="form-footer">
                    <button id="reviewClose" type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button id="reviewRegister" class="btn rental">등록하기</button>
                  </div>

            </div>
        </div>
    </div>

</div>

<script>
        let errorMsg = "${errorMsg}";
        let failed = "${failed}";
</script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
<script src="../../js/detailPage.js"></script>
</body>
</html>