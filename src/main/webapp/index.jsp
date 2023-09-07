<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<header>
    <nav class="navbar index-nav">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1"><img src="./css/icons/nest_eco_leaf.png" /> </span>
            <span></span>
            <span>
                <button type="button" class=" btn1 btn-sm">로그인</button>
                <button type="button" class=" btn2 btn-sm">회원가입</button>
            </span>
        </div>
    </nav>
</header>

<section>
    <div class="title">
        <h1>LeafPage</h1>
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

</section>

</body>
</html>