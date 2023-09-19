package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminBookDeleteController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("나들어왔당");
        String ISBN = request.getParameter("ISBN");
        System.out.println(ISBN);

        BookDTO dto = new BookDTO();
        dto.setISBN(ISBN);
        BookDAO dao = new BookDAO();
        int count = dao.bookDelete(dto);

        if(count == 1){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('삭제성공')");
            out.println("</script>");
            return "booklistView.do";
        }else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('삭제실패')");
            out.println("history.back()");
            out.println("</script>");
        }


        return "booklistView.do";
    }
}
