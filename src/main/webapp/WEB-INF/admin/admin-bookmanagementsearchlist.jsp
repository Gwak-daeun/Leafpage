<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>도서 관리 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/admin-page-style.css" />
</head>

<div class="modal fade" id="editModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="editBook" aria-hidden="true">
    <div class="modal-dialog modal-xl" >
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="editBook">도서 관리</h1>

            </div>
            <div class="modal-body">
                <div class="modal-container">
                    <form action="/books/edit.do" method="post">
                        <div class="edit-container">


                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button onclick="closeModal()" type="button" class="btn btn-secondary" data-bs-dismiss="modal" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

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
        </ul>
    </aside>
</aside>
<div class="container">
    <form action="adminbooksearch.do" method="post">
        <div class="top">
            <div class="float-end">
                <input class="search" name="keyword" type="text" value="${keyword}" placeholder="검색어 입력" />
                <input class="search-btn" type="submit" value="검색" />
            </div>
        </div>
    </form>
    <div class="main-frame">
        <div class="list-title">
            <h3>도서 현황</h3>
            <!-- TODO: button class 추가 후 변경 -->
            <!-- Button trigger modal -->
            <button type="button" onclick="modalOn()" class="register-btn openModalLink" data-bs-toggle="modal" data-bs-target="#editModal" >
                도서 추가
            </button>
        </div>
        <hr />
        <div class="list-view">
            <div class="list-header">
                <ul class="list-rows">
                    <li class="table-header">ISBN</li>
                    <li><span class="v-line"></span></li>
                    <li class="table-header">도서명</li>
                    <li><span class="v-line"></span></li>
                    <li class="table-header">저자명</li>
                    <li><span class="v-line"></span></li>
                    <li class="table-header">출판사</li>
                    <li><span class="v-line"></span></li>
                    <li class="table-header">카테고리</li>
                </ul>
            </div>
            <div class="list-body">
                <c:forEach var="book" items="${bookList}">
                    <ul class="list-rows">
                        <li class="table-value" >
                            <a href="javascript:void(0)" onclick="modalOn(`${book.ISBN}`, )" class="openModalLink" data-isbn="${book.ISBN}" data-bs-toggle="modal" data-bs-target="#editModal"><span class="isbn"> ${book.ISBN}</span></a></li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">${book.bookName}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">${book.bookAuthorName}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">${book.bookPublisherName}</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value"><c:forEach var="category" items="${book.categories}">
                            #${category} </c:forEach></li>
                    </ul>
                </c:forEach>
            </div>
        </div>
        <hr />
        <div class="list-footer">
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${pageDTO.prev }">
                        <li class="page-item">
                            <a class="page-link" href="adminbooksearch.do?pageNum=${pageDTO.startPage - 1 }">이전</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="adminbooksearch.do?pageNum=${pageDTO.startPage - 1 }">이전</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="num" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">
                    <li class="page-item">
                        <a class="${pageDTO.pageNum eq num ? 'active' : '' } page-link" href="adminbooksearch.do?pageNum=${num }">${num }</a>
                    </li>
                </c:forEach>
                <c:choose>
                    <c:when test="${pageDTO.next }">
                        <li class="page-item">
                            <a class="page-link" href="adminbooksearch.do?pageNum=${pageDTO.endPage + 1 }">다음</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="adminbooksearch.do?pageNum=${pageDTO.endPage + 1 }">다음</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
<!-- Modal -->

<div id="background_modal" class="back background_modal"></div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="/js/admin-management.js"></script>
</html>