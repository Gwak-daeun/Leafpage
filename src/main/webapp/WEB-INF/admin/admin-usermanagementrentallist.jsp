<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>유저 관리 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous"/>
    <link rel="stylesheet" href="/css/admin-page-style.css"/>
</head>
<body>
<aside>
    <aside class="side-bar">
        <ul>
            <li>
                <a href="/userlistview.do">유저</a>
            </li>
            <li>
                <a href="/booklistView.do">도서</a>
            </li>
            <li>
                <a href="/indexInfo.do">메인페이지</a>
            </li>
            <li>
                <a href="/logout.do">로그아웃</a>
            </li>
        </ul>
    </aside>
</aside>
<div class="container">

    <div class="top">
        <form action="adminusersearch.do" method="post">
            <div>
                <input class="search" name="keyword" type="text" placeholder="검색어 입력"/>
                <input class="search-btn" type="submit" value="검색"/>
            </div>
        </form>
        <select id="select-order" name="order" onchange="if(this.value) location.href=(this.value);">
            <option value="/userlistview.do">대여 횟수 많은 유저</option>
            <option value="/userlistsignupView.do">최근 가입한 유저</option>
        </select>
    </div>

    <div class="main-frame">
        <div class="list-title">
            <h3>대여 횟수 많은 유저</h3>
        </div>
        <hr/>
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
                <c:forEach var="user" items="${userList}">
                    <ul class="list-rows">
                        <li class="table-value">${user.userId}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">${user.userJoiningDate}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">${user.userEmail}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">${user.userTel}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">${user.userState}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">
                            <form class="form-state" action="/userstatechange.do?userId=${user.userId}" method="post"
                                  onsubmit="return confirmAndSubmit(event);">
                                <select class="select-user-state-modify" name="state" id="userStateSelect">
                                    <option value="black">블랙</option>
                                    <option value="dormant">휴면</option>
                                    <option value="General">일반</option>
                                    <option value="delete">회원 삭제</option>
                                </select>
                                <input class="state-btn" type="submit" value="저장"/>
                            </form>

                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>
        <hr/>
        <div class="list-footer">
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${pageDTO.prev }">
                        <li class="page-item">
                            <a class="page-link" href="userlistview.do?pageNum=${pageDTO.startPage - 1 }">이전</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="userlistview.do?pageNum=${pageDTO.startPage - 1 }">이전</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="num" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">
                    <li class="page-item">
                        <a class="${pageDTO.pageNum eq num ? 'active' : '' } page-link"
                           href="userlistview.do?pageNum=${num }">${num }</a>
                    </li>
                </c:forEach>
                <c:choose>
                    <c:when test="${pageDTO.next }">
                        <li class="page-item">
                            <a class="page-link" href="userlistview.do?pageNum=${pageDTO.endPage + 1 }">다음</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="userlistview.do?pageNum=${pageDTO.endPage + 1 }">다음</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="/js/admin-management.js"></script>

</html>