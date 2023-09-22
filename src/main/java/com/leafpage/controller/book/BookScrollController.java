package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookScrollController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String sortWord = request.getParameter("sortWord");

        String searchSelect = request.getParameter("searchSelect");

        String searchKeyword = request.getParameter("searchKeyword");

        String genre = "";

        if (!request.getParameter("genre").isEmpty()) {
            genre = request.getParameter("genre");
            request.setAttribute("genre", genre);
        }

        String page = "0";

        if (request.getParameter("page") != null) {
            page = request.getParameter("page");
            request.setAttribute("page", page);
        }

        System.out.println("CHECK SORTWORD : " + sortWord);

        List<BookDTO> books = new BookDAO().SortBooks(sortWord, searchSelect, searchKeyword, genre, page);

        request.setAttribute("books", books);

        request.setAttribute("searchSelect", searchSelect);

        return null;
    }
}
