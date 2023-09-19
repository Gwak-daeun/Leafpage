<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/updateMyInfo.css">
</head>
<body>
<%@include file="./component/header.jsp"%>

<section class="umi_container">
    <div class="my-info-form">
        <h4>내 정보</h4>
        <form method="post" action="#">
            <div class="form-group  mt-3">
                <input type="text" name="#" class="form-control" placeholder="아이디">
            </div>
            <div class="form-group mt-3">
                <input type="password" name="#" class="form-control" placeholder="현재 비밀번호">
            </div>
            <div class="form-group mt-3">
                <input type="password" name="#" class="form-control" placeholder="새 비밀번호">
            </div>
            <div class="form-group mt-3">
                <input type="password" name="#" class="form-control" placeholder="새 비밀번호 확인">
            </div>
            <div class="form-group mt-3">
                <input type="number" name="#" class="form-control" placeholder="기존 전화번호">
            </div>
            <div class="form-group mt-3">
                <input type="email" name="#" class="form-control" placeholder="기존 이메일">
            </div>
            <div class="redtext">(* 이메일 변경 후 재 인증 필요)</div>
            <div class="btn-flex-group">
                <button class="btn btn-danger mt-3" href="#">탈퇴하기</button>
                <button type="submit" class="btn btn-success btn3 mt-3">수정하기</button>
            </div>
        </form>
    </div>
</section>

<!-- 제이쿼리 자바스크립트 추가하기 -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
</body>
</html>
