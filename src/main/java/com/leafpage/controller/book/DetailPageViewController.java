package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.gwakdao.BookDAO;
import com.leafpage.gwakdao.ReviewDAO;
import com.leafpage.gwakdto.BookDetailDTO;
import com.leafpage.gwakdto.ReviewDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailPageViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String ISBN = "040501813854";

        List<ReviewDTO>  reviews = new ReviewDAO().findReviews(ISBN);

        BookDetailDTO bookDetail = new BookDAO().getBookDetails(ISBN);

        HttpSession session = request.getSession();

        session.setAttribute("bookDetail", bookDetail);

        session.setAttribute("reviews", reviews);

        return "detailPage";
    }
}
