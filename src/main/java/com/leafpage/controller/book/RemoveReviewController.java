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
            // 정상적으로 변환된 경우에 수행할 작업
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // 오류 처리: 숫자로 변환할 수 없는 경우
            // 오류 메시지를 출력하거나 다른 조치를 취할 수 있음
        }

        HttpSession session = request.getSession();

        if (result == -1) {
            session.setAttribute("errorMsg", "리뷰 삭제에 실패했어요.");
            return "/detailPageView.do";
        }

        session.setAttribute("errorMsg", "");

        return "/detailPageView.do";
    }
}
