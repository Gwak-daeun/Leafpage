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

        String isbn = "1010101010101";

        BookDAO bookDAO = new BookDAO();

        List<ReviewDTO>  reviews = new ReviewDAO().findReviews(isbn);

        List<BookDTO> sameAuthorBooks = bookDAO.findSameAuthorBooks(isbn);

        BookDTO bookDetail = bookDAO.getBookDetails(isbn);

        request.setAttribute("bookDetail", bookDetail);

        request.setAttribute("reviews", reviews);

        request.setAttribute("sameAuthorBooks", sameAuthorBooks);

        return "book/detailPage";
    }
}
