<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="css/index.css">
    <title>LeafPage</title>
</head>
<body>
<%@include file="./WEB-INF/component/header.jsp"%>

    <div class="container">
        <div class="title">
            <h1>LeafPage</h1>
        </div>
        <form method="get" action="search.do">
            <div class="search-bar">
                <span>
                    <select id="selectBox" name="searchSelect" class="tbook-selec">
                    <option value="전체" class="book-option">전체</option>
                    <option value="출판사" class="book-option">출판사</option>
                    <option value="제목" class="book-option">제목</option>
                    <option value="작가" class="book-option">작가</option>
                    </select>
                </span>
                <span class="search-box">
                    <input type="text" id="search" name="searchKeyword" class="search-input" placeholder="둘러보기" />
                </span>
                <span>
                    <button type="submit" style="border: none; background: none; padding: 0; cursor: pointer;">
                        <img id="searchLens" class="search-lens" style="width: 40px; height: 40px; margin-right: 5px; margin-top: 3px;" src="./css/icons/search.png" />
                    </button>
                </span>

            </div>
        </form>

        <div class="book-box">
            <ul class="book-ul">
                <c:forEach var="book" items="${mainBooks}">
                    <li>
                        <a href="/detailPageView.do?isbn=${book.ISBN}">
                            <div class="book-list">
                                <img class="book-cover" src="${book.bookImg}" />
                                <div class="book-title">${book.bookName}</div>
                                <div class="book-author">${book.bookAuthorName}</div>
                            </div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/alertMsg.js"></script>

</body>
</html>