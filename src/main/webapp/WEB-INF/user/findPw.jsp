<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/findpw.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%@include file="../component/header.jsp"%>


<div class="main container-fluid" >
    <h3>비밀번호를 찾기 위해 <br>필요한 정보를 입력하세요.</h3>

    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="아이디">
    </div>

    <div class="mb-3">
        <select class="form-select" id="selectType" aria-label=" select">
            <option selected>email</option>
            <option value="1">전화번호</option>
        </select>

    </div>
    <div class="mb-3" id="phone">
        <input type="text" class="form-control" placeholder="전화번호">
    </div>

    <div class="mb-3" id="email">
        <input type="email" class="form-control" placeholder="이메일">
    </div>

    <div class="input-group mb-3">
        <select class="form-select" aria-label=" select">
            <option selected>나의 보물 1호는?</option>
            <option value="1">출생지는 어디인가요?</option>
            <option value="2">첫 번째 자동차 모델은 무엇인가요?</option>
            <option value="3">초등학교 이름은 무엇인가요?</option>
        </select>
    </div>

    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="비밀번호 찾기 답">

    </div>


    <div class="form-floating mb-4">
        <input type="submit"  class="btn3 btn btn-success" value="비밀번호 찾기">
    </div>

</div>
<script type="text/javascript" src="../../js/select.js"></script>
</body>
</html>

