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
<c:set var="currentTel" value="${userTel}"/>
<c:set var="currentEmail" value="${userEmail}"/>
<%@include file="../component/header.jsp"%>

<section class="umi_container">
    <div class="my-info-form">
        <h4>내 정보</h4>
        <form name="update_form" method="post" action="updateUserInfo.do">
            <div class="form-group  mt-3">
                <label for="current_user_id">아이디</label>
                <input readonly type="text" id="current_user_id" name="current_user_id" class="form-control" placeholder="아이디" value="${userId}">
            </div>
            <div class="form-group mt-3">
                <label for="current_user_tel">휴대전화번호</label>
                <input type="number" id="current_user_tel" name="TelForUpdate" class="form-control" placeholder="기존 전화번호" value="${userTel}" data-user-tel="${userTel}">
                <div class="redtext" id="checkSpan_for_tel_update"></div>
            </div>

            <div class="form-group mt-3">
                <label for="current_user_email">이메일 주소</label>
                <input type="email" id="current_user_email" name="EmailForUpdate" class="form-control" placeholder="기존 이메일" value="${userEmail}" data-user-email="${userEmail}">
                <div class="redtext" id="checkSpan_for_email_update"></div>
            </div>

            <div class="form-group mt-3">
                <label for="password_for_update">정보를 변경하시려면 비밀번호를 입력하세요.</label>
                <input type="password" id="password_for_update" name="passwordForUpdate" class="form-control" placeholder="현재 비밀번호">
                <div class="redtext" id="checkSpan_password_for_update"></div>
            </div>
            <div class="redtext"> 정보를 변경한 후에는 이메일 인증을 다시 진행해주세요. </div>
            <div class="btn-flex-group">
                <button type="button" class="btn btn-success btn3 mt-3" onclick="updateMyInfo()" >수정하기</button>
            </div>
        </form>
        <button id='checkWithdrawal' data-bs-toggle='modal' data-bs-target='#withdrawalCheckModal' class='btn btn-danger mt-3'>탈퇴하기</button>
        <button class='btn btn-success mt-3' onclick="inactive()">휴면전환</button>
        <button data-bs-toggle='modal' data-bs-target='#newPasswordModal' class='btn btn-success mt-3'>비밀번호<br>변경</button>

    </div>
</section>

<%@include file="../component/withdrawalCheckModal.jsp"%>
<%@include file="../component/changePasswordModal.jsp"%>

<script>
    let currentTel = "${currentTel}";
    let currentEmail = "${currentEmail}";
</script>

<!-- 제이쿼리 자바스크립트 추가하기 -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/updateMyInfo.js"></script>
<script type="text/javascript" src="../../js/findPw.js"></script>
<script type="text/javascript" src="../../js/alertMsg.js"></script>
</body>
</html>
