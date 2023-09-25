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

        HttpSession session = request.getSession();

        Long userNo = (Long) session.getAttribute("userNo");

        String isbn = request.getParameter("isbn");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String reviewDate = formatter.format(date);

        int reviewRating = Integer.parseInt(request.getParameter("rating"));

        String reviewContent = request.getParameter("content");

        if (reviewContent.isEmpty() || reviewRating == 0) {
            System.out.println("REVIEW FAILED");
            request.setAttribute("failed", "앗, 리뷰 별점과 내용을 작성해주세요.");
            return "/detailPageView.do";
        }

        System.out.println("CHECK REVIEW VALUES : " + reviewContent + ", " + reviewRating);

        int result = new ReviewDAO().makeReview(userNo, isbn, reviewDate, reviewContent, reviewRating);

        if (result != 1) {
            System.out.println("REVIEW FAILED");
            request.setAttribute("failed", "리뷰 등록에 실패했어요.");
            return "/detailPageView.do";
        }

        request.setAttribute("failed", "");

        return "/detailPageView.do";
    }
}
