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

        //Todo: 이후 로그인한 유저에서 받아오는 파라미터 값으로 바꿔야 함
        int userNo = 1;

        userBooks = bookDAO.getUserLendingBook(userNo);

        userReturnedBooks = bookDAO.getUserReturnedBook(userNo);

        request.setAttribute("books", userBooks);

        request.setAttribute("userReturnedBooks", userReturnedBooks);

        return "user/mypage";
    }
}
