package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dto.BookDTO;
import com.leafpage.dao.BookDAO;
import com.leafpage.dao.ReviewDAO;
import com.leafpage.dto.ReviewDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DetailPageController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String ISBN = "040501813854";

        List<ReviewDTO>  reviews = new ReviewDAO().findReviews(ISBN);

        BookDTO bookDetail = new BookDAO().getBookDetails(ISBN);

        HttpSession session = request.getSession();

        session.setAttribute("bookDetail", bookDetail);

        session.setAttribute("reviews", reviews);

        return "book/detailPage";
    }
}
