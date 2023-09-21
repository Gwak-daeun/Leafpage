package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.ReviewDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeReviewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int userNum = 1;

        String ISBN = "040501813854";

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String reviewDate = formatter.format(date);

        int reviewRating = Integer.parseInt(request.getParameter("rating"));

        String reviewContent = request.getParameter("content");

        System.out.println("CHECK REVIEW VALUES : " + reviewContent + ", " + reviewRating);

        int result = new ReviewDAO().makeReview(userNum, ISBN, reviewDate, reviewContent, reviewRating);

        HttpSession session = request.getSession();

        if (result != 1) {
            session.setAttribute("failed", "리뷰 등록에 실패했어요.");
            return "/detailPageView.do";
        }

        session.setAttribute("failed", "");

        return "/detailPageView.do";
    }
}
