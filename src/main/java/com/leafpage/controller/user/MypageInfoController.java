package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.LikeyDAO;
import com.leafpage.dto.BookContentDTO;
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

        ArrayList<MypageBooksDTO> userBooks = new ArrayList<>();
        ArrayList<MypageReturnedBooksDTO> userReturnedBooks = new ArrayList<>();
        List<BookContentDTO> bookText = new ArrayList<>();

        LikeyDAO.BookDAO bookDAO = new LikeyDAO.BookDAO();

        int totalRentals = 0;

        userBooks = bookDAO.getUserLendingBook();

        userReturnedBooks = bookDAO.getUserReturnedBook();

        totalRentals = bookDAO.getTotalRentals();

        bookText = bookDAO.getLendingBookContent();

        HttpSession session = request.getSession();

        session.setAttribute("books", userBooks);

        session.setAttribute("totalRentals", totalRentals);

        session.setAttribute("userReturnedBooks", userReturnedBooks);

        session.setAttribute("bookText", bookText);

        return "/user/mypage";
    }
}
