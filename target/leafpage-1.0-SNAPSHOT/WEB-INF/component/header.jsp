<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="navbar index-nav">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1"><img src="css/icons/nest_eco_leaf.png" onclick="location.href='indexInfo.do'"/> </span>
            <div>
                <c:if test="${userId != null}">
                    <span class="welcome">${userId}님</span>
                    <c:if test="${userEmailChecked != true}">
                        <span class="welcome">, 서비스를 이용하려면 <a href="emailResendView.do">이메일 인증</a>을 완료해주셔야 합니다.</span>
                    </c:if>
                </c:if>
            </div>
            <div>
                <c:if test="${userId == null}">
                    <button type="button" class=" btn2 btn-sm" onclick="location.href='signupView.do'">회원가입</button>
                    <a ><button type="button" class=" btn1 btn-sm" onclick="location.href='loginView.do'">로그인</button></a>
                </c:if>
                <c:if test="${userId != null}">
                    <button type="button" class="btn2 btn-sm" onclick="location.href='mypageInfo.do'">마이페이지</button>
                    <button type="button" class="btn1 btn-sm" onclick="logoutConfirm()">로그아웃</button>
                </c:if>
            </div>
        </div>
    </nav>
</header>

<input type="hidden" id="msg" value="${msg}">