package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;
import com.leafpage.dto.MypageBooksDTO;
import com.leafpage.dto.MypageReturnedBooksDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MypageInfoController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<MypageBooksDTO> userBooks = new ArrayList<>();
        List<MypageReturnedBooksDTO> userReturnedBooks = new ArrayList<>();
        List<BookDTO> bookText = new ArrayList<>();

        BookDAO bookDAO = new BookDAO();

        int totalRentals = 0;

        userBooks = bookDAO.getUserLendingBook();

        userReturnedBooks = bookDAO.getUserReturnedBook();

//        totalRentals = bookDAO.getTotalRentals();

//        bookText = bookDAO.getLendingBookContent();

        HttpSession session = request.getSession();

        session.setAttribute("books", userBooks);

//        session.setAttribute("totalRentals", totalRentals);

        session.setAttribute("userReturnedBooks", userReturnedBooks);

//        session.setAttribute("bookText", bookText);

        return "/user/mypage";
    }
}
