package com.leafpage.controller.user.View;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 이미 로그인 중인지 확인한다.
        String userId = (String) request.getAttribute("userId");

        if (userId != null) {
            HttpSession session = request.getSession();
            session.setAttribute("msg", "이미 로그인 상태입니다.");
            return "index";
        }
        System.out.println("로그인화면으로 이동");
        return "user/login";
    }
}