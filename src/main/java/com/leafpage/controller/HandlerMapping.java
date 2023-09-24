package com.leafpage.controller;

import com.leafpage.controller.rental.RentalController;
import com.leafpage.controller.rental.ReturnController;
import com.leafpage.controller.book.BookYController;
import com.leafpage.controller.book.DetailPageController;
import com.leafpage.controller.book.MakeReviewController;
import com.leafpage.controller.book.RemoveReviewController;
import com.leafpage.controller.user.*;
import com.leafpage.controller.admin.*;
import com.leafpage.controller.user.View.*;


import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private final Map<String, Controller> mappings;

    public HandlerMapping() {

        mappings = new HashMap<>();

        mappings.put("/indexInfo.do", new IndexController());
        mappings.put("/LikeHeart.do", new LikeHeartController());
        mappings.put("/detailPageView.do", new DetailPageController());
        mappings.put("/clearMsg.do", new ClearMsgController());

        mappings.put("/booklistView.do", new AdminBookListController());
        mappings.put("/getBook.do", new AdminBookDetailController());
        mappings.put("/books/edit.do", new AdminBookEditController());
        mappings.put("/mypageInfo.do", new MypageInfoController());
        mappings.put("/updateMyInfoView.do", new UpdateMypageInfoViewController());
        mappings.put("/saveUserBookY.do", new BookYController());
        mappings.put("/makeReview.do", new MakeReviewController());
        mappings.put("/removeReview.do", new RemoveReviewController());
        mappings.put("/userlistview.do", new AdminUserListController());
        mappings.put("/userlistsignupView.do", new AdminUserSignUpListController());
        mappings.put("/userstatechange.do", new AdminUserStateChangeController());

        mappings.put("/booklistView.do", new AdminBookListController());
        mappings.put("/getBook.do", new AdminBookDetailController());
        mappings.put("/bookupload.do", new AdminBookUploadController());
        mappings.put("/remove.do", new AdminBookDeleteController());
        mappings.put("/edit.do", new AdminBookEditController());
        mappings.put("/adminbooksearch.do", new AdminBookSearchListController());
        mappings.put("/adminusersearch.do", new AdminUserSearchListController());

        mappings.put("/findIdView.do", new FindIdViewController());
        mappings.put("/findPwView.do", new FindPwViewController());
        mappings.put("/findPw.do", new FindPwController());
        mappings.put("/findId.do", new FindIdController());
        mappings.put("/signupView.do", new SignupViewController());
        mappings.put("/signup.do", new SignupController());
        mappings.put("/loginView.do", new LoginViewController());
        mappings.put("/login.do", new LoginController());
        mappings.put("/duplicateIdCheck.do", new DuplicateIdCheckController());
        mappings.put("/sendEmail.do", new SendEmailController());
        mappings.put("/sendEmailView.do", new SendEmailViewController());
        mappings.put("/checkEmail.do", new CheckEmailController());
        mappings.put("/logout.do", new LogoutController());
        mappings.put("/loginCheck.do", new LoginCheckController());
        mappings.put("/emailResendView.do", new EmailResendViewController());
        mappings.put("/successEmailCheckView.do", new SuccessEmailCheckViewController());
        mappings.put("/failEmailCheckView.do", new FailEmailCheckViewController());
        mappings.put("/withdrawal.do", new WithdrawalController());
        mappings.put("/changeNewPassword.do", new ChangeNewPasswordController());
        mappings.put("/updateUserInfo.do", new UpdateUserInfoController());
        mappings.put("/inactive.do", new InactiveController());

        mappings.put("/rentBook.do", new RentalController());
        mappings.put("/returnBook.do", new ReturnController());
    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}
