package com.leafpage.controller.user.View;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SignupViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") != null) {
            session.setAttribute("msg", "이미 로그인 상태입니다.");
            return "index";
        }

        System.out.println("회원가입 화면으로 이동");
        return "user/userSignup";
    }
}