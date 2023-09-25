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
        System.out.println("회원가입 화면으로 이동");
        HttpSession session = request.getSession();

        if (isUserLoggedIn(session)) {
            redirectToIndexPageWithMessage(session, response, "이미 로그인 상태입니다.");
        }

        return "user/userSignup";
    }

    private boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("userId") != null;
    }

    private void redirectToIndexPageWithMessage(HttpSession session, HttpServletResponse response, String message) throws IOException {
        session.setAttribute("msg", message);
        response.sendRedirect("indexInfo.do");
    }

}