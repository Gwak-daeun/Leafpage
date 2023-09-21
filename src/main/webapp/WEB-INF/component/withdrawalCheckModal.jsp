<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="withdrawalCheckModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal">탈퇴 확인</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form name="new_pw_form" action="withdrawal.do" method="post">
                    <div class="form-group">
                        <label for="checkPassword">회원 탈퇴를 원하시면, 현재 비밀번호를 입력해주세요.</label>
                        <input type="password" id="checkPassword" name="checkPassword" class="form-control" maxlength="30">
                    </div>
                    <div id="checkSpanForWithdrawal"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                        <button type="button" class="btn btn-danger mt-3" onclick="withdrawal()">탈퇴하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

