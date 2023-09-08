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
        <!-- <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked" checked>
        </div> -->
        <!-- <label>
            <input autocomplete="dkdk" value="dskf" role="switch" type="checkbox" />
            <span>알람</span>
          </label> -->
    </div>
    <div class="search-bar">
        <select class="book-select">
            <option class="book-option">국내도서</option>
            <option class="book-option">해외도서</option>
        </select>
        <input placeholder="둘러보기" />
        <img class="search-lens" src="./css/icons/search.png" />
    </div>
    <div class="book-box">
        <ul>
            <li><div class="book-list"></div></li>
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

</body>
</html>