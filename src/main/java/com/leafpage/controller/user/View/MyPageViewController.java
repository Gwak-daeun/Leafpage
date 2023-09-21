package com.leafpage.controller.user.View;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyPageViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("마이페이지로 이동");

        return "user/mypage";
    }
}