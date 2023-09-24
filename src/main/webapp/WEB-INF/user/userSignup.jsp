<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/userSign.css">

</head>
<body>
<%@include file="../component/header.jsp"%>

<div class="main container-fluid">
    <h3>회원가입을 위해 <br>정보를 입력해주세요.</h3>
    <form name="signup_form" method="post" action="signup.do" >

        <label class="signup form-label checkId">아이디 </label>
        <p class='checkIdSpan checkText'></p>
        <div class="input-group">
            <input type="text" name="userId" id="userId" class="form-control" placeholder="아이디" aria-label="Recipient's username" aria-describedby="button-addon2">
            <button type="button" class="btn1 btn-outline-secondary" id="button-addon2" onclick="duplicateIdCheck()">중복확인</button>
        </div>
        <label  class="signup form-label checkPassword">비밀번호</label>
        <p class='checkPasswordSpan checkText'></p>
        <div class="input-group">
            <input type="password" name="userPassword" id="userPassword" class="form-control" placeholder="비밀번호">
        </div>

        <label  class="signup form-label checkPasswordConfirm">비밀번호 확인</label>
        <p class='checkPasswordConfirmSpan checkText'></p>
        <div class="input-group">
            <input type="password" name="userPasswordConfirm" id="userPasswordConfirm" class="form-control" placeholder="비밀번호 확인">
        </div>

        <label class="signup form-label checkEmail">이메일</label>
        <p class='checkEmailSpan checkText'></p>
        <div class="input-group">
            <input type="email" name="userEmail" id="userEmail" class="form-control" placeholder="mega@zone.com">
        </div>

        <label class="signup form-label checkTel">휴대전화번호 (하이픈(-)을 빼고 입력하세요.)</label>
        <p class='checkTelSpan checkText'></p>
        <div class="input-group">
            <input type="number" name="userTel" id="userTel" class="form-control" placeholder="01012341234">
        </div>

        <label class="signup form-label checkSecurityQuestion">비밀번호 찾기 질문</label>
        <p class='checkSecurityQuestionSpan checkText'></p>
        <div class="input-group">
            <select class="form-select" name="userSecurityQuestion" id="userSecurityQuestion" aria-label="select">
                <option selected>나의 보물 1호는?</option>
                <option value="1">출생지는 어디인가요?</option>
                <option value="2">첫 번째 자동차 모델은 무엇인가요?</option>
                <option value="3">초등학교 이름은 무엇인가요?</option>
            </select>
        </div>

        <label class="signupPW form-label checkSecurityAnswer">비밀번호 찾기 답</label>
        <p class='checkSecurityAnswerSpan checkText'></p>
        <div class="input-group mb-3">
            <input type="text" name="userSecurityAnswer" id="userSecurityAnswer" class="form-control" placeholder="비밀번호 찾기 답">
        </div>

        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="userConsent" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
                개인정보 수집 및 정보이용에 동의 합니다
            </label>
        </div>

        <div class="form-floating">
            <button type="button" id="signup_btn" class="btn3 btn btn-success" onclick="signupCheck();">회원가입</button>
        </div>

    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" crossorigin="anonymous"></script>
<script src="../../js/userSignup.js"></script>
<script src="../../js/alertMsg.js"></script>

</body>
</html>