package com.leafpage.controller.book;

import com.leafpage.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveReviewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("CHECK : " + request.getParameter("reviewNo"));

        int reviewNo = Integer.parseInt(request.getParameter("reviewNo")) ;

        if (reviewNo != 1) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "리뷰 삭제에 실패했어요.");
        }

        return "/detailPageView.do";
    }
}
