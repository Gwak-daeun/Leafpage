<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/login.css?ver=2023091401">
</head>
<body>
<%@include file="../component/header.jsp"%>

<div class="main container-fluid">
    <h3>LeafPage</h3>
    <form name="login_form" method="post" action="login.do">
        <div class="idInput">
            <img style="height: 25px; width: 25px; margin-left: 8px; margin-bottom: 5px;" src="../../css/icons/account_circle.png">
            <input class="inputBox" name="userId" id="loginUserId" type="text" placeholder="ID">
        </div>

        <div class="pwInput">
            <img style="height: 20px; width: 18px; margin-left: 12px; margin-bottom: 5px; margin-right: 3px;" src="../../css/icons/lock.png">
            <input class="inputBox" name="userPassword" id="loginUserPassword" type="password" placeholder="Password">
        </div>
        <a class="findid" href="findIdView.do">아이디 찾기</a> <p>|</p> <a class="findpw" href="findPwView.do">비밀번호 찾기</a>
        <div>
            <span class='login_check checkText'></span>
        </div>
        <div class="form-floating">
            <input type="button" id="login_btn" class="login btn btn-success"  value="Login" onclick="loginCheck();">
        </div>

        <input type="button" class="signup" onclick="location.href='signupView.do'">
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" crossorigin="anonymous"></script>
<script src="../../js/login.js"></script>
<script type="text/javascript" src="../../js/alertMsg.js"></script>
</body>
</html>