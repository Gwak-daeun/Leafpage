<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/findpw.css">
</head>
<body>
<%@include file="../component/header.jsp"%>


<form class="main container-fluid" >
    <h3>비밀번호를 찾기 위해 <br>필요한 정보를 입력하세요.</h3>

    <div class="input-group mb-3">
        <input type="text" class="form-control" id="inputId" placeholder="아이디">
    </div>

    <div class="mb-3">
        <select class="form-select" id="selectType" aria-label=" select">
            <option selected value="findByEmail">email</option>
            <option value="findByTel">전화번호</option>
        </select>

    </div>
    <div class="mb-3" id="phone">
        <input type="text" class="form-control" id="inputTel" placeholder="전화번호">
    </div>

    <div class="mb-3" id="email">
        <input type="email" class="form-control" id="inputEmail" placeholder="이메일">
    </div>

    <div class="input-group mb-3">
        <select class="form-select" aria-label="select" id="selectQuestion">
            <option selected>나의 보물 1호는?</option>
            <option value="1">출생지는 어디인가요?</option>
            <option value="2">첫 번째 자동차 모델은 무엇인가요?</option>
            <option value="3">초등학교 이름은 무엇인가요?</option>
        </select>
    </div>

    <div class="input-group mb-3">
        <input type="text" class="form-control" id="inputAnswer" placeholder="비밀번호 찾기 답">

    </div>

    <div class="alert alert-success mt-4" role="alert" id="update_pw"></div>

    <div class="form-floating mb-4">
        <input type="button" id="find_pw_btn" class="btn3 btn btn-success" value="비밀번호 찾기" onclick="checkNullPw()">
    </div>

</form>
<%@include file="../component/changePasswordModal.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

<script type="text/javascript" src="../../js/alertMsg.js"></script>
<script type="text/javascript" src="../../js/select.js"></script>
<script type="text/javascript" src="../../js/findPw.js"></script>

</body>
</html>

