package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<BookDTO> mainBookList = new ArrayList<>();

        mainBookList = BookDAO.getInstance().findMainBooks();

        request.setAttribute("mainBooks", mainBookList);

        return "index";
    }
}
