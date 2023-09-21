package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminBookListController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BookDAO dao = new BookDAO();

        BookDTO dto = new BookDTO();
        List<BookDTO> bookList = dao.booklist();
        request.setAttribute("bookList" ,bookList);



        return "admin/admin-bookmanagement";
    }
}
