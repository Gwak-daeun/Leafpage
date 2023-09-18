<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="${book.rental_no}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">${book.book_name}</h1>
            </div>
            <div class="modal-body">
                <c:forEach var="text" items="${bookText}" >
                    ${text}
                        <br />
                </c:forEach>
            </div>
            <div class="modal-footer">
                <button id="closeBtn" type="button" class="modal-close" data-bs-dismiss="modal">여기까지 보기</button>
            </div>
        </div>
    </div>
</div>