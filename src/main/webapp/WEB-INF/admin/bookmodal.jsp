<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
<link rel="stylesheet" href="/css/admin-page-style.css" />
<form class="editform" action="/edit.do" method="post" enctype="multipart/form-data">
<div class="modal-left-div">
    <img src="${book.bookimgFullPath}"  id="image">
    <div class="filebox">
        <label for="image-upload">이미지 수정</label>
        <input type="file" name="bookimg" id="image-upload" accept=".jpg, .png, .jpeg">
    </div>
    <button type="reset" class="remove-btn" onclick="">이미지 삭제</button>

    <input class="book-edit-btn" type="submit" value="도서 수정" />
    <button type="button" onclick="location.href='/remove.do?ISBN=${book.ISBN}';" class="book-remove btn btn-danger">도서 삭제</button>

</div>

<div class="modal-right-div" id = "modalContent">
    <input type="text" name="ISBN" class="normal-input" required aria-required="true" placeholder="ISBN" value="${book.ISBN}">
    <input type="text" name="bookname" class="normal-input" required aria-required="true" placeholder="도서명" value="${book.bookname}">
    <input type="text" name="auther" class="normal-input" required aria-required="true" placeholder="저자명" value="${book.auther}">
    <input type="text" name="publisher" class="normal-input" required aria-required="true" placeholder="출판사" value="${book.publisher}">
    <input type="date" name="pubdate" class="normal-input" required aria-required="true" data-placeholder="출판일" value="${book.pubdate}">
    <input type="text" name="categories" class="normal-input" placeholder="카테고리" value="${book.categories}">
    <textarea name="bookinfo" class="book-intro" cols="30" rows="10" required aria-required="true" placeholder="책 소개">${book.bookinfo}</textarea>
    <textarea name="bookchapter" class="book-chapter" cols="30" rows="10" required aria-required="true" placeholder="책 목차">${book.bookchapter}</textarea>
    <textarea name="bookcontent"  class="book-content" cols="30" rows="10" required aria-required="true" placeholder="책 내용">${book.bookcontent}</textarea>
</div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="/js/admin-management.js"></script>
<script>
</script>