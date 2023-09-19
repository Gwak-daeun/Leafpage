package com.leafpage.controller;

import com.leafpage.controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private Map<String, Controller> mappings;

    public HandlerMapping() {
        mappings = new HashMap<>();
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
    }

    public Controller getController(String path) {
        System.out.println("mappingPath: "+mappings.get(path));
        return mappings.get(path);
    }
}
