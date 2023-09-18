package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminBookDetailController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ISBN = request.getParameter("ISBN");
        BookDAO dao = new BookDAO();
        System.out.println("들어옴");
        System.out.println(ISBN);


        BookDTO dto = new BookDTO();
        BookDTO book = dao.deleteBook(ISBN);
        System.out.println(book.getBookcontent());
        System.out.println("여기");
        request.setAttribute("book" ,book);

        return "admin/bookmodal";
    }
}
