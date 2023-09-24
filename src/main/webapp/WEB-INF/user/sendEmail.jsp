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

<section class="container mt-3" style="max-width:560px;">
    <div class="alert alert-success mt-4" role="alert">
        이메일 주소 인증 메일이 성공적으로 발송되었습니다.<br>
        회원가입 시 입력하신 이메일 주소로 이동하여 인증을 완료해주세요.<br>
        이메일 인증을 완료하지 않을 경우, 서비스 이용에 제한이 있을 수 있습니다.<br>
    </div>
</section>

</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/alertMsg.js"></script>
</html>