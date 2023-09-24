package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.ReviewDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveReviewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int result = 0;

        try {
            int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
            result = new ReviewDAO().removeReview(reviewNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (result == -1) {
            request.setAttribute("errorMsg", "리뷰 삭제에 실패했어요.");
            return "/detailPageView.do";
        }

        request.setAttribute("errorMsg", "");

        return "/detailPageView.do";
    }
}
