<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/notFoundPage.css">
</head>
<header>
    <nav class="navbar index-nav">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1"><img src="css/icons/nest_eco_leaf.png"
                                                    onclick="location.href='/indexInfo.do'"/> </span>
        </div>
    </nav>
</header>
<body>

<div class="container">
    <h1>잘못된 접근입니다.</h1>
    <button class="btn1" onclick="location.href=`javascript:window.history.back();`">뒤로가기</button>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
</body>
</html>