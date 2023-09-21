<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="newPasswordModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal">새 비밀번호 변경</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form name="new_pw_form" action="changeNewPassword.do" method="post">
                    <div class="form-group">
                        <label for="newPassword">새 비밀번호</label>
                        <input type="password" id="newPassword" name="newPassword" class="form-control" maxlength="30">
                    </div>
                    <div class="form-group">
                        <label for="confirmNewPassword">비밀번호 확인</label>
                        <input type="password" id="confirmNewPassword" name="confirmNewPassword" class="form-control" maxlength="30">
                    </div>
                    <div id="checkSpan"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                        <button type="button" class="btn btn-success" onclick="newPasswordCheck()">변경하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script type="text/javascript" src="../../js/findPw.js"></script>
