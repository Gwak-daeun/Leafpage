<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
<link rel="stylesheet" href="/css/admin-page-style.css" />

<div class="modal-left-div">
    <img src="/css/icons/iconmonstr-book-26-240.png" id="image">
    <div class="filebox">
        <label for="image-upload">이미지 수정</label>
        <input type="file" id="image-upload" accept=".jpg, .png, .jpeg">
    </div>
    <button type="reset" class="remove-btn" onclick="">이미지 삭제</button>
    <input class="book-edit-btn" type="submit" value="도서 등록" />
</div>

<div class="modal-right-div" id = "modalContent">
    <input type="text" class="normal-input" required aria-required="true" placeholder="ISBN" value="${book.ISBN}">
    <input type="text" class="normal-input" required aria-required="true" placeholder="도서명" value="${book.bookname}">
    <input type="text" class="normal-input" required aria-required="true" placeholder="저자명" value="${book.auther}">
    <input type="text" class="normal-input" required aria-required="true" placeholder="출판사" value="${book.publisher}">
    <input type="date" class="normal-input" required aria-required="true" data-placeholder="출판일" value="${book.pubdate}">
    <input type="text" class="normal-input" placeholder="카테고리" value="${book.categories}">
    <textarea name="book-intro" class="book-intro" cols="30" rows="10" required aria-required="true" placeholder="책 소개">${book.bookinfo}</textarea>
    <textarea name="book-chapter" class="book-chapter" cols="30" rows="10" required aria-required="true" placeholder="책 목차">${book.bookchapter}</textarea>
    <textarea name="book-content" class="book-content" cols="30" rows="10" required aria-required="true" placeholder="책 내용">${book.bookcontent}</textarea>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="/js/admin-management.js"></script>
<script>
</script>