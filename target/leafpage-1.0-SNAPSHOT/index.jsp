<%@ page import="com.leafpage.dao.UserDAO" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="css/index.css">
    <title>LeafPage</title>
</head>
<body>
<%@include file="./WEB-INF/component/header.jsp"%>

<%
    String SQL = "INSERT INTO connection_test VALUES (null, ?, 1, true)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    DataSource dataSource = null;
    try {
        Context init = new InitialContext();
        dataSource = (DataSource)init.lookup("java:comp/env/jdbc/mysql");
        conn = dataSource.getConnection();
        pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, "나들어왔엉!!!");
        System.out.println(pstmt.executeUpdate());
        pstmt.executeUpdate();  //executeUpdate : 실제로 영향을 받은 데이터의 개수 반환 => 성공한다면 1 반환됨
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        try {if(pstmt != null) pstmt.close();} catch (Exception e) {e.printStackTrace();}
        try {if(rs != null) rs.close();} catch (Exception e) {e.printStackTrace();}
    }
%>

<div class="container">
    <div class="title">
        <h1>LeafPage</h1>
    </div>

    <div class="search-bar">
        <select class="book-select">
            <option class="book-option">국내도서</option>
            <option class="book-option">해외도서</option>
        </select>
        <input class="search-input" placeholder="둘러보기" />
        <img class="search-lens" src="./css/icons/search.png" />
    </div>
    <div class="book-box">
        <ul class="book-ul ">
            <li data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                <div class="book-list">
                    <img class="book-cover" src="./image/4.jpg" />
                    <div class="book-title">나를 지키는 관계...</div>
                    <div class="book-author">안젤라 센</div>
                </div>
            </li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
            <li><div class="book-list"></div></li>
        </ul>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>