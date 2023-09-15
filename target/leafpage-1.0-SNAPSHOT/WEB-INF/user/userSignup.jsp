<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/userSign.css">
</head>
<body>
<header>
    <nav class="navbar index-nav">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1"><img src="../../css/icons/nest_eco_leaf.png" /> </span>
            <span></span>
            <span>
                <button type="button" class=" btn2 btn-sm"  >회원가입</button>
                <button type="button" class=" btn1 btn-sm" >로그인</button>
            </span>
        </div>
    </nav>
</header>


<div class="main container-fluid">
    <h3>회원가입을 위해 <br>정보를 입력해주세요.</h3>
    <label class="signup form-label">아이디 </label>
    <div class="input-group">
        <input type="text" class="form-control" placeholder="아이디" aria-label="Recipient's username" aria-describedby="button-addon2">
        <button class="btn1 btn-outline-secondary" type="button" id="button-addon2">중복확인</button>
    </div>

    <label  class="signup form-label">비밀번호</label>
    <div class="input-group">
        <input type="password" class="form-control" placeholder="비밀번호">
    </div>
    <label  class="signup form-label">비밀번호 확인</label>
    <div class="input-group">
        <input type="password" class="form-control" placeholder="비밀번호 확인">
    </div>
    <label class="signup form-label">이메일</label>
    <div class="input-group">
        <input type="email" class="form-control" placeholder="mega@megazone.com">
    </div>
    <label class="signup form-label">전화번호</label>
    <div class="input-group">
        <input type="number" class="form-control" placeholder="01012341234">
    </div>
    <label class="signup form-label">비밀번호 찾기 질문</label>
    <div class="input-group">
        <select class="form-select" aria-label=" select">
            <option selected>나의 보물 1호는?</option>
            <option value="1">출생지는 어디인가요?</option>
            <option value="2">첫 번째 자동차 모델은 무엇인가요?</option>
            <option value="3">초등학교 이름은 무엇인가요?</option>
        </select>
    </div>
    <label class="signupPW form-label">비밀번호 찾기 답</label>
    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="비밀번호 찾기 답">

    </div>

    <div class="form-check mb-3">
        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
        <label class="form-check-label" for="flexCheckDefault">
            개인정보 수집 및 정보이용에 동의 합니다
        </label>
    </div>

    <div class="form-floating">
        <input type="submit"  class="btn3 btn btn-success" value="회원가입">
    </div>

</div>
</body>
</html>