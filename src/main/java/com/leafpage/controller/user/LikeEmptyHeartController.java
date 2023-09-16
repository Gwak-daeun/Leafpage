package com.leafpage.controller.user;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeEmptyHeartController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("좋아요 추가합니다");

        //1. 사용자 정보 입력 추출
        long userNo = Long.parseLong(request.getParameter("userNo"));


        //2. DB 연동 처리

        //3. 화면 이동

        return "Test";
    }
}