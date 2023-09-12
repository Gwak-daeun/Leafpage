<%--
  Created by IntelliJ IDEA.
  User: 내컴
  Date: 2023-09-07
  Time: 오후 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="styleSheet" href="css/searchResult.css">
    <title>Document</title>
</head>
<body>
<header>
    <nav class="navbar index-nav">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1"><img src="css/icons/nest_eco_leaf.png" /> </span>
            <span></span>
            <span>
                        <button type="button" class=" btn1 btn-sm">로그인</button>
                        <button type="button" class=" btn2 btn-sm">회원가입</button>
                    </span>
        </div>
    </nav>
</header>

<div class="container">
    <div class="title">
        <h1>LeafPage</h1>
    </div>
    <div class="toggle">
        <label class="switch">
            <input type="checkbox" class="switch-input">
            <span class="switch-label" data-on="최신순" data-off="인기순"></span>
            <span class="switch-handle"></span>
        </label>

    </div>
    <div class="search-bar">
        <select class="book-select">
            <option class="book-option">국내도서</option>
            <option class="book-option">해외도서</option>
        </select>
        <input class="search-input" placeholder="둘러보기" />
        <img class="search-lens" src="./css/icons/search.png" />
    </div>
    <div class="search-keywords">
        <ul>
            <li>에세이</li>
            <li>소설</li>
            <li>만화</li>
            <li>생활</li>
            <li>학술논문</li>
        </ul>
    </div>
    <div class="book-box">
        <ul class="book-ul list-group list-group-horizontal">
            <li >
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
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script></body>
</body>
</html>