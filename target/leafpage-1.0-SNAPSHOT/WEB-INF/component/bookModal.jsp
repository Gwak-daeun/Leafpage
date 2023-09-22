<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="${book.rentalNo}" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">${book.bookName}</h1>
                <a class="select-mode">
                    <img class="light-icon" src="../css/icons/light_mode.png" />
                    <img class="dark-icon" src="../css/icons/dark_mode.png" />
                </a>
                <%--                <button class="select-mode">모드</button>--%>
            </div>
            <div class="modal-body">
                ${book.bookContent}
            </div>
            <div class="modal-footer">
                <button onclick="sendY(${book.rentalNo})" id="closeBtn" type="button" class="modal-close" data-bs-dismiss="modal">여기까지 보기</button>
            </div>
        </div>
    </div>
</div>