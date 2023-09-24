<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/login.css?ver=2023091401">
</head>
<body>
<%@include file="../component/header.jsp"%>

<section class="container mt-3" style="max-width:560px;">
    <div class="alert alert-success mt-4" role="alert">
        이메일 주소 인증에 성공하여 정상적인 서비스 이용이 가능합니다.
        <button onclick="location.href='index.jsp'">메인페이지로 이동</button>
    </div>
</section>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/alertMsg.js"></script>
</body>
</html>