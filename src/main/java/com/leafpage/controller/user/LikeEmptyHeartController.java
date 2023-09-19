package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.LeeLikeyDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeEmptyHeartController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("좋아요 추가합니다");

        //1. 사용자 정보 입력 추출
        long userNo = Long.parseLong(request.getParameter("userNo").trim());
        String isbn = request.getParameter("isbn").trim();
        System.out.println(userNo);
        System.out.println(isbn);

        //2. DB 연동 처리
        LeeLikeyDAO leeLikeyDAO = new LeeLikeyDAO();

        boolean like = leeLikeyDAO.like(userNo, isbn);
        System.out.println(like);
        int heartCount = leeLikeyDAO.likeCount(isbn);
        System.out.println(heartCount);

        //3. 화면 이동

        HttpSession session = request.getSession();

        //request정보 담기
        int likecheck = like ? 1 : 0;

        PrintWriter out = response.getWriter();
        out.print(likecheck);
        out.print(heartCount);
        out.close();

        return "detailPageView.do";
    }
}