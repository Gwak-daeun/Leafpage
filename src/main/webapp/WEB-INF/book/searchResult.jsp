<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="styleSheet" href="../../css/searchResult.css">
    <title>Document</title>
</head>
<body>
<%@include file="../component/header.jsp"%>

<div class="container">
    <div class="title">
        <h1>LeafPage</h1>
    </div>
    <form method="get" action="sortBooks.do">
        <div class="toggle">
            <a href="/sortBooks.do?sortWord=최신순&searchKeyword=${searchKeyword}&searchSelect=${searchSelect}&genre=${genre}"><div id="btn1">최신순</div></a>
            <a href="/sortBooks.do?sortWord=인기순&searchKeyword=${searchKeyword}&searchSelect=${searchSelect}&genre=${genre}"><div id="btn2">인기순</div></a>
        </div>
        <div class="search-bar">
            <select id="selectBox" name="searchSelect" class="book-select">
                <option value="전체" class="book-option">전체</option>
                <option value="출판사" class="book-option">출판사</option>
                <option value="제목" class="book-option">제목</option>
                <option value="작가" class="book-option">작가</option>
            </select>
            <input value="${searchKeyword}" class="search-input" placeholder="둘러보기" />
            <img class="search-lens" src="../../css/icons/search.png" />
        </div>
    </form>
    <div class="search-keywords">
        <ul>
            <li> <a href="/sortBooks.do?sortWord=최신순&searchKeyword=${searchKeyword}&searchSelect=${searchSelect}&genre=에세이">에세이</a></li>
            <li><a href="/sortBooks.do?sortWord=최신순&searchKeyword=${searchKeyword}&searchSelect=${searchSelect}&genre=소설">소설</a></li>
            <li><a href="/sortBooks.do?sortWord=최신순&searchKeyword=${searchKeyword}&searchSelect=${searchSelect}&genre=만화">만화</a></li>
            <li><a href="/sortBooks.do?sortWord=최신순&searchKeyword=${searchKeyword}&searchSelect=${searchSelect}&genre=생활">생활</a></li>
            <li> <a href="/sortBooks.do?sortWord=최신순&searchKeyword=${searchKeyword}&searchSelect=${searchSelect}&genre=학술논문">학술논문</a></li>
        </ul>
    </div>
    <div class="book-box">
        <ul class="book-ul list-group list-group-horizontal">
            <c:forEach var="searchBook" items="${books}">
                <li>
                    <a href="/detailPageView.do?isbn=${searchBook.ISBN}">
                        <div class="book-list">
                            <img class="book-cover" src="${searchBook.bookImg}" />
                            <div class="book-title">${searchBook.bookName}</div>
                            <div class="book-author">${searchBook.bookAuthorName}</div>
                        </div>
                    </a>
                </li>

            </c:forEach>


        </ul>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script></body>

</body>
</html>