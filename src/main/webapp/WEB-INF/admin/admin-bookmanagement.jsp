<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>도서 관리 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/admin-page-style.css" />
</head>
<!-- Modal -->
<div class="modal fade" id="editModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
    aria-labelledby="editBook" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="editBook">도서 추가/수정/삭제</h1>
                <form action="/books/remove?IBSN=?" method="post">
                    <button type="submit" class="btn btn-danger">도서 삭제</button>
                </form>
            </div>
            <div class="modal-body">
                <div class="modal-container">
                    <form action="/books/edit" method="post">
                        <div class="edit-container">
                            <div class="modal-left-div">
                                <img src="/css/icons/iconmonstr-book-26-240.png" id="image">
                                <div class="filebox">
                                    <label for="image-upload">이미지 수정</label>
                                    <input type="file" id="image-upload" accept=".jpg, .png, .jpeg">
                                </div>
                                <button type="reset" class="remove-btn" onclick="">이미지 삭제</button>
                                <input class="book-edit-btn" type="submit" value="도서 등록" />
                            </div>
                            <div class="modal-right-div">
                                <input type="text" class="normal-input" required aria-required="true"
                                    placeholder="ISBN">
                                <input type="text" class="normal-input" required aria-required="true" placeholder="도서명">
                                <input type="text" class="normal-input" required aria-required="true" placeholder="저자명">
                                <input type="text" class="normal-input" required aria-required="true" placeholder="출판사">
                                <input type="date" class="normal-input" required aria-required="true"
                                    data-placeholder="출판일" required aria-required="true">
                                <input type="text" class="normal-input" placeholder="카테고리">
                                <textarea name="book-intro" class="book-intro" cols="30" rows="10" required
                                    aria-required="true" placeholder="책 소개"></textarea>
                                <textarea name="book-chapter" class="book-chapter" cols="30" rows="10" required
                                    aria-required="true" placeholder="책 목차"></textarea>
                                <textarea name="book-content" class="book-content" cols="30" rows="10" required
                                    aria-required="true" placeholder="책 내용"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<body>
    <aside>
        <aside class="side-bar">
            <ul>
                <li>
                    <a href="/WEB-INF/admin/admin-usermanagement.jsp">유저</a>
                </li>
                <li>
                    <a href="/WEB-INF/admin/admin-bookmanagement.html">도서</a>
                </li>
            </ul>
        </aside>
    </aside>
    <div class="container">
        <form action="books.do" method="post">
            <div class="top">
                <div class="float-end">
                    <input class="search" type="text" placeholder="검색어 입력" />
                    <input class="search-btn" type="submit" value="검색" />
                </div>
            </div>
        </form>
        <div class="main-frame">
            <div class="list-title">
                <h3>도서 현황</h3>
                <!-- TODO: button class 추가 후 변경 -->
                <!-- Button trigger modal -->
                <button type="button" class="register-btn" data-bs-toggle="modal" data-bs-target="#editModal">
                    도서 추가
                </button>
            </div>
            <hr />
            <div class="list-view">
                <div class="list-header">
                    <ul class="list-rows">
                        <li class="table-header">ISBN</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-header">도서명</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-header">저자명</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-header">출판사</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-header">카테고리</li>
                    </ul>
                </div>
                <div class="list-body">
                    <ul class="list-rows">
                        <li class="table-value" data-bs-toggle="modal" data-bs-target="#editModal"><span
                                class="isbn">979-11-90052-21-4</span></li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">연하이고 남편이고 주부입니다만</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">왕찬현, 기해경</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">파람북</li>
                        <li><span class="v-line"></span></li>
                        <li class="table-value">에세이</li>
                    </ul>
                </div>
            </div>
        </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="/js/admin-management.js"></script>

</html>