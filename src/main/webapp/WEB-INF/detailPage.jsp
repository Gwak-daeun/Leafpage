<!-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="css/detailPage.css">
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
    <div class="inline">
        <div class="mg ">
            <img src="image/bookcover.png" class="imgstyle">
            <div class="center">
                <span class="inline"><h2>1%를 읽는 힘</h2></span>
                <span >메르 저</span>
                <div>카테고리 | 출판사 | 출판일</div>
                <div class="bottom-mg inline">
                    <button class="btn rental" data-toggle="modal" href="#rental">대여하기</button>
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
                    <div class="contexth">매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근
                        매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근
                        매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근
                        매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근
                        매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근

                        매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근
                        매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근

                        매일 하나씩 필명으로 올리는 글을 통해 시장을 바라보는 남다른 통찰력을 제공하며 많은 이들을 열광하게 한 메르의 첫 책이다.
                        사건에 대한 단편적인 시선이 아닌 꼬리에 꼬리를 무는 생각 확장법을 통해 정보가 연결되고 숨은 기회를 찾는 새로운 시각을 만나보자.
                        - 경제경영 MD 김상근

                    </div>


                </div>
                <div class="tab-content">
                    <div>목차
                        프롤로그

                        1장 오래된 시각과 새로운 해석

                        01 반도체는 패권 국가 경쟁의 핵심키
                        02 전기차와 배터리 전쟁의 미래
                        03 OPEC 플러스와 미국이 싸우는 이유
                        04 슈퍼 사이클을 준비하는 한국의 조선업
                        ...
                    </div>
                </div>
                <div class="tab-content">
                    <div class="enroll-right">
                        <button class="btn rental " data-toggle="modal" href="#reviewenroll">등록하기</button>
                    </div>
                    <ul class="review-content">
                        <li>
                            <div class="card">
                                <div class="card-header">
                                  <div class="row">
                                    <div class="card-title">
                                        <span>
                                            ★★★
                                        </span>
                                        <span class="review-top-right">
                                            <p>작성자&nbsp; 2023.05.25</p>
                                        </span>
                                    </div>
                                  </div>
                                </div>
                                <!--강의명 밑 내용과 추천-->
                                <div class="card-body">

                                  <p class="card-text">
                                    블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다 블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다 블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다 블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다 블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다
                                    </p>
                                  <div class="row">
                                    <div class="col-9 text-left">
                                    </div>
                                    <!--강의 추천하는지 오른쪽 아래 위치-->
                                    <div class="col-3 text-right">
                                      <a onclick="return confirm('삭제하시겠습니까?')" href="">삭제</a>
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </li>
                        <li>
                            <div class="card">
                                <div class="card-header">
                                  <div class="row">
                                    <div class="card-title">
                                        <span>
                                            ★★★
                                        </span>
                                        <span class="review-top-right">
                                            <p>작성자&nbsp; 2023.05.25</p>
                                        </span>
                                    </div>
                                  </div>
                                </div>
                                <!--강의명 밑 내용과 추천-->
                                <div class="card-body">

                                  <p class="card-text">
                                    블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다
                                    </p>
                                  <div class="row">
                                    <div class="col-9 text-left">
                                    </div>
                                    <!--강의 추천하는지 오른쪽 아래 위치-->
                                    <div class="col-3 text-right">
                                      <a onclick="return confirm('삭제하시겠습니까?')" href="">삭제</a>
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </li>
                        <li>
                            <div class="card">
                                <div class="card-header">
                                  <div class="row">
                                    <div class="card-title">
                                        <span>
                                            ★★★★★
                                        </span>
                                        <span class="review-top-right">
                                            <p>작성자&nbsp; 2023.05.25</p>
                                        </span>
                                    </div>
                                  </div>
                                </div>
                                <!--강의명 밑 내용과 추천-->
                                <div class="card-body">

                                  <p class="card-text">
                                    블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다
                                    </p>
                                  <div class="row">
                                    <div class="col-9 text-left">
                                    </div>
                                    <!--강의 추천하는지 오른쪽 아래 위치-->
                                    <div class="col-3 text-right">
                                      <a onclick="return confirm('삭제하시겠습니까?')" href="">삭제</a>
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </li>
                        <li>
                            <div class="card">
                                <div class="card-header">
                                  <div class="row">
                                    <div class="card-title">
                                        <span>
                                            ★★★★
                                        </span>
                                        <span class="review-top-right">
                                            <p>작성자&nbsp; 2023.05.25</p>
                                        </span>
                                    </div>
                                  </div>
                                </div>
                                <!--강의명 밑 내용과 추천-->
                                <div class="card-body">
                                  <p class="card-text">
                                    블로그에 글 몇개만 읽어만봐도 이책의 구매이유는 분명합니다
                                    </p>
                                  <div class="row">
                                    <div class="col-9 text-left">
                                    </div>
                                    <!--강의 추천하는지 오른쪽 아래 위치-->
                                    <div class="col-3 text-right">
                                      <a onclick="return confirm('삭제하시겠습니까?')" href="">삭제</a>
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </li>
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
                <p>대여기간 2023-09-28</p>
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
                <h5 class="modal-title" id="modal">리뷰 등록</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><!--모달 창을 닫도록-->
                    <span aria-hidden="true">&times;</span><!--닫기 아이콘-->
                </button>
            </div>
            <div class="modal-body">
                <form action="" method="post">
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
                    <textarea name="reviewContent" class="form-control" maxlength="2048" style="height: 180px">
                      
                    </textarea>
                  </div>
                  <div class="form-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button type="submit" class="btn rental">등록하기</button>
                  </div>
                </form>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
<script src="js/detailPage.js"></script>
</body>
</html>