package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;
import com.leafpage.dto.MypageBooksDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class MypageInfoController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ArrayList<MypageBooksDTO> userBooks = new ArrayList<>();

        userBooks = new BookDAO().getUserBookInfo();

        HttpSession session = request.getSession();

        session.setAttribute("books", userBooks);

        return "mypage";
    }
}
