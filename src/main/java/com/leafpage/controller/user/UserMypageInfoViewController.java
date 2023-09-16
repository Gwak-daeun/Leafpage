package com.leafpage.controller.user;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserMypageInfoViewController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("MOVE TO MYINFO");

        return "updateMyInfo";
    }
}
