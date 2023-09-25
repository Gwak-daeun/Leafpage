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
        HttpSession newSession = request.getSession();
        newSession.setAttribute("msg", "로그아웃 되었습니다.");
        response.sendRedirect("indexInfo.do");
        return "none";
    }
}
