package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String searchSelect = request.getParameter("searchSelect");

        String searchKeyword = request.getParameter("searchKeyword");

        String page = "0";

        System.out.println("CHECK KEYWORDS: " + searchSelect + searchKeyword);

        List<BookDTO> books = BookDAO.getInstance().searchBooks(searchSelect, searchKeyword, page);

        request.setAttribute("books", books);

        request.setAttribute("searchSelect", searchSelect);

        request.setAttribute("searchKeyword", searchKeyword);

        request.setAttribute("page", 12);

        return "book/searchResult";
    }
}
