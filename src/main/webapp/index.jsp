<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="css/index.css">
    <title>LeafPage</title>
</head>
<body>
<%@include file="./WEB-INF/component/header.jsp"%>

<%
    response.sendRedirect("/LikeHeart.do?userNo=9&isbn=040501813-4");
%>

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
            <li data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                <div class="book-list">
                    <img class="book-cover" src="./image/4.jpg" />
                    <div class="book-title">나를 지키는 관계...</div>
                    <div class="book-author">안젤라 센</div>
                </div>
            </li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
        </ul>
    </div>
    <button onclick="location.href='/booklistView.do' ">관리자</button>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>