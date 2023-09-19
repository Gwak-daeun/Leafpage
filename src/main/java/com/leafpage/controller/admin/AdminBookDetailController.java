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
        if(ISBN==null){
            BookDTO book = dao.detailBook(ISBN);
            System.out.println(book.getBookimgFullPath());
            request.setAttribute("book" ,book);
            return "admin/bookmodal";
        }else{
            BookDTO book = dao.detailBook(ISBN);
            System.out.println(book.getBookimgFullPath());
            request.setAttribute("book" ,book);

            return "admin/bookmodaledit";
        }


    }
}
