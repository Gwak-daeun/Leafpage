<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>유저 관리 페이지</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
  <link rel="stylesheet" href="/css/admin-page-style.css" />
</head>

<body>
  <aside>
    <aside class="side-bar">
      <ul>
        <li>
          <a href="/WEB-INF/admin/admin-usermanagement.jsp">유저</a>
        </li>
        <li>
          <a href="/WEB-INF/admin/admin-bookmanagement.jsp">도서</a>
        </li>
      </ul>
    </aside>
  </aside>
  <div class="container">
    <form action="users.do" method="post">
      <div class="top">
        <div>
          <input class="search" type="text" placeholder="검색어 입력" />
          <input class="search-btn" type="submit" value="검색" />
        </div>
        <select id="select-order" name="order">
          <option value="#rentcount">대여 횟수 많은 유저</option>
          <option value="#recently">최근 가입한 유저</option>
        </select>
      </div>
    </form>
    <div class="main-frame">
      <div id="rentcount" class="tab-contents">
        <div class="list-title">
          <h3>대여 횟수 많은 유저</h3>
        </div>
        <hr />
        <div class="list-view">
          <div class="list-header">
            <ul class="list-rows">
              <li class="table-header">userID</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">가입일</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">이메일</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">전화번호</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">상태</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">상태 전환</li>
            </ul>
          </div>
          <div class="list-body">
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div id="recently" class="tab-contents">
        <div class="list-title">
          <h3>최근 가입한 유저</h3>
        </div>
        <hr />
        <div class="list-view">
          <div class="list-header">
            <ul class="list-rows">
              <li class="table-header">userID</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">가입일</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">이메일</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">전화번호</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">상태</li>
              <li><span class="v-line"></span></li>
              <li class="table-header">상태 전환</li>
            </ul>
          </div>
          <div class="list-body">
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
            <ul class="list-rows">
              <li class="table-value">Donguk</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">23.09.23</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">Donguk@gmail.com</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">010-1234-5678</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">일반 회원</li>
              <li><span class="v-line"></span></li>
              <li class="table-value">
                <form class="form-state" action="user/state/modify.do?userId=1">
                  <select class="select-user-state-modify" name="state">
                    <option value="black">블랙</option>
                    <option value="dormant">휴면</option>
                    <option value="delete">회원 삭제</option>
                  </select>
                  <input class="state-btn" type="submit" value="저장" />
                </form>
              </li>
            </ul>
          </div>
        </div>
      </div>

    </div>
  </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="/js/admin-management.js"></script>

</html>