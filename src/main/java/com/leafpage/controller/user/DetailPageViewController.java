package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.gwakdao.BookDAO;
import com.leafpage.gwakdto.BookDetailDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class DetailPageViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String ISBN = "040501813854";

//        ArrayList<BookDetailDTO> bookDetail = new ArrayList<>();

        BookDetailDTO bookDetail = new BookDetailDTO();

        bookDetail = new BookDAO().getBookDetails(ISBN);

        HttpSession session = request.getSession();

        session.setAttribute("bookDetail", bookDetail);

        return "detailPage";
    }
}
