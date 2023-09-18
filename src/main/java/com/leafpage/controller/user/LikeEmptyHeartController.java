package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.LikeyDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LikeEmptyHeartController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("좋아요 추가합니다");

        //1. 사용자 정보 입력 추출
        long userNo = Long.parseLong(request.getParameter("userNo").trim());
        String isbn = request.getParameter("isbn").trim();

        //2. DB 연동 처리
        LikeyDAO likeyDAO = new LikeyDAO();

        boolean like = likeyDAO.like(userNo, isbn);
        int heartCount = likeyDAO.likeCount(isbn);

        //3. 화면 이동

        HttpSession session = request.getSession();

        //request정보 담기
        session.setAttribute("heartClick", like);
        session.setAttribute("heartCount", heartCount);

        return "detailPageView.do";
    }
}