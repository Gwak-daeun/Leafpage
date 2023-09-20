package com.leafpage.controller;

import com.leafpage.controller.rental.RentalController;
import com.leafpage.controller.user.*;
import com.leafpage.controller.admin.*;


import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private Map<String, Controller> mappings;

    public HandlerMapping() {

        mappings = new HashMap<>();

        mappings.put("/LikeHeart.do",new LikeEmptyHeartController());
        mappings.put("/detailPageView.do", new DetailPageViewController());
        mappings.put("/booklistView.do", new AdminBookListController());
        mappings.put("/getBook.do", new AdminBookDetailController());
        mappings.put("/bookupload.do", new AdminBookUploadController());
        mappings.put("/remove.do", new AdminBookDeleteController());
        mappings.put("/edit.do", new AdminBookEditController());

        mappings.put("/signupView.do", new SignupViewController());
        mappings.put("/signup.do", new SignupController());
        mappings.put("/loginView.do", new LoginViewController());
        mappings.put("/login.do", new LoginController());
        mappings.put("/adminUserManagementView.do", new AdminUserManagementViewController());
        mappings.put("/duplicateIdCheck.do", new DuplicateIdCheckController());
        mappings.put("/sendEmail.do", new SendEmailController());
        mappings.put("/sendEmailView.do", new SendEmailViewController());
        mappings.put("/checkEmail.do", new CheckEmailController());
        mappings.put("/logout.do", new LogoutController());
        mappings.put("/loginCheck.do", new LoginCheckController());
        mappings.put("/emailResendView.do", new EmailResendViewController());
        mappings.put("/myPageView.do", new MyPageViewController());
        mappings.put("/successEmailCheckView.do", new SuccessEmailCheckViewController());
        mappings.put("/failEmailCheckView.do", new FailEmailCheckViewController());

        mappings.put("/rentBook.do", new RentalController());
    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}
