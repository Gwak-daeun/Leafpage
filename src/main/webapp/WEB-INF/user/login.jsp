<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-09-11
  Time: 오후 4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/login.css?ver=2023091401">
</head>
<body>
<header>
    <nav class="navbar index-nav">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1"><img src="../../css/icons/nest_eco_leaf.png" /> </span>
            <span></span>
            <span>
                <a href="signupView.do"><button type="button" class="btn2 btn-sm" >회원가입</button></a>
                <a href="loginView.do"><button type="button" class="btn1 btn-sm " >로그인</button></a>
            </span>
        </div>
    </nav>
</header>


<div class="main container-fluid">
    <h3>LeafPage</h3>
    <form method="post" action="login.do">
        <input class="idInput" name="userId" type="text" placeholder="ID">

        <input class="pwInput" name="userPassword" type="text" placeholder="Password">
        <a class="findid" href="findId.jsp">아이디 찾기</a> <p>|</p> <a class="findpw" href="findPw.jsp">비밀번호 찾기</a>

        <div class="form-floating">
            <input type="submit"  class="login btn btn-success"  value="Login">
        </div>

        <input type="button" class="signup" onclick="location.href='userSignupView.do' ">
    </form>
</div>
</body>
</html>