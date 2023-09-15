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
    }

    public Controller getController(String path) {
        System.out.println("mappingPath: "+mappings.get(path));
        return mappings.get(path);
    }
}
