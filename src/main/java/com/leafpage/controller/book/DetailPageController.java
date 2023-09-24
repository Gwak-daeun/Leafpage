package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.LeeLikeyDAO;
import com.leafpage.dto.BookDTO;
import com.leafpage.dao.BookDAO;
import com.leafpage.dao.ReviewDAO;
import com.leafpage.dto.ReviewDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

public class DetailPageController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Long userNo = ((Integer) session.getAttribute("userNo")).longValue();
        System.out.println("userNo"+userNo);
        String isbn = request.getParameter("isbn");

        BookDAO bookDAO = new BookDAO();

        boolean isBookAvailable = bookDAO.isISBNZero(isbn);

        System.out.println("CHECK RESULT IS BOOK : " + isBookAvailable);

        if (!isBookAvailable) {
            return "notFoundPageView.do";
        }

        List<ReviewDTO>  reviews = new ReviewDAO().findReviews(isbn);

        List<BookDTO> sameAuthorBooks = bookDAO.findSameAuthorBooks(isbn);

        LeeLikeyDAO leeLikeyDAO = new LeeLikeyDAO();
        int checkLike = leeLikeyDAO.checkLike(userNo, isbn);
        int heartCount = leeLikeyDAO.likeCount(isbn);
        System.out.println("CHECKLIKE" + checkLike);


        BookDTO bookDetail = bookDAO.getBookDetails(isbn);



        request.setAttribute("bookDetail", bookDetail);

        request.setAttribute("reviews", reviews);

        request.setAttribute("sameAuthorBooks", sameAuthorBooks);

        request.setAttribute("heartSelect", checkLike);
        request.setAttribute("heartCount", heartCount);

        return "book/detailPage";
    }
}
