package com.leafpage.controller.user;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("로그아웃 처리");
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }
}
