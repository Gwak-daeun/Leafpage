package com.leafpage.controller.user.View;

import com.leafpage.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindPwViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("비밀번호 찾기 페이지로 이동");
        return "user/findPw";
    }
}
