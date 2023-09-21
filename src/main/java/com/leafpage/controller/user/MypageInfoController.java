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

        BookDAO bookDAO = new BookDAO();

        int userNo = 1;

        userBooks = bookDAO.getUserLendingBook(userNo);

        userReturnedBooks = bookDAO.getUserReturnedBook(userNo);


        HttpSession session = request.getSession();

        session.setAttribute("books", userBooks);

        session.setAttribute("userReturnedBooks", userReturnedBooks);

        return "/user/mypage";
    }
}
