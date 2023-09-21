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
    <div class="search-bar">
        <select class="book-select">
            <option class="book-option">국내도서</option>
            <option class="book-option">해외도서</option>
        </select>
        <input class="search-input" placeholder="둘러보기" />
        <img class="search-lens" src="./css/icons/search.png" />
    </div>
    <div class="book-box">
        <ul class="book-ul ">
            <c:forEach var="book" items="${mainBooks}">
            <li>
                <a href="">
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>