<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>LeafPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/findId.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%@include file="../component/header.jsp"%>

<form class="main container-fluid">
    <h3>아이디를 찾기 위해 <br>이메일 또는 전화번호를 <br>입력해주세요.</h3>

    <div class="mb-3">
        <select class="form-select" id="selectType" aria-label=" select">
            <option selected value="findByEmail" >email</option>
            <option value="findByTel" >전화번호</option>
        </select>

    </div>
    <div class="mb-3" id="phone">
        <input type="text" class="form-control" id="inputTel" placeholder="전화번호">
    </div>

    <div class="mb-3" id="email">
        <input type="email" class="form-control" id="inputEmail" placeholder="이메일">
    </div>

    <div class="alert alert-success mt-4" role="alert" id="found_id"></div>

    <div class="form-floating mb-4">
        <input type="button" id="find_id_btn" class="btn3 btn btn-success" value="아이디 찾기" onclick="checkNull()">
    </div>

</form>
<script type="text/javascript" src="../../js/select.js"></script>
<script type="text/javascript" src="../../js/findId.js"></script>
</body>
</html>