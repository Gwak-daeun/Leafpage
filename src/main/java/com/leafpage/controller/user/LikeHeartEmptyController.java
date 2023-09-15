package com.leafpage.controller.user;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeHeartEmptyController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("좋아요 추가합니다");

        return "Test";
    }
}