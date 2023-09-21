<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="../../css/updateMyInfo.css">
</head>
<body>
<%@include file="../component/header.jsp"%>

<section class="umi_container">
    <div class="my-info-form">
        <h4>내 정보</h4>
        <form method="post" action="updateUserInfo.do">
            <div class="form-group  mt-3">
                <input type="text" name="#" class="form-control" placeholder="아이디" value=${userId}>
            </div>
            <div class="form-group mt-3">
                <input type="number" name="#" class="form-control" placeholder="기존 전화번호" value="${userTel}">
            </div>
            <div class="form-group mt-3">
                <input type="email" name="#" class="form-control" placeholder="기존 이메일" value="${userEmail}">
            </div>
            <div class="redtext">(* 이메일 변경 후 재 인증 필요)</div>
            <div class="form-group mt-3">
                <input type="password" name="#" class="form-control" placeholder="현재 비밀번호">
            </div>
            <div class="btn-flex-group">
                <button id='checkWithdrawal' data-bs-toggle='modal' data-bs-target='#withdrawalCheckModal' class='btn btn-danger mt-3'>탈퇴하기</button>
                <button data-bs-toggle='modal' data-bs-target='#newPasswordModal' class='btn btn-success mt-3'>비밀번호 변경</button>
                <button type="button" class="btn btn-success btn3 mt-3" >수정하기</button>
                <a id='change_pw_btn' data-bs-toggle='modal' data-bs-target='#newPasswordModal' class='btn btn-success mx-1 mt-2'>비밀번호 변경</a>
            </div>
        </form>
    </div>
</section>

<%@include file="../component/withdrawalCheckModal.jsp"%>
<%@include file="../component/changePasswordModal.jsp"%>

<!-- 제이쿼리 자바스크립트 추가하기 -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/updateMyInfo.js"></script>
</body>
</html>
