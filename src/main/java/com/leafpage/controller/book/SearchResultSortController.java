package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class SearchResultSortController implements Controller {
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

        int pageNum = 0;

        if (request.getParameter("page") != null) {
            page = request.getParameter("page");

            try {
                pageNum = Integer.parseInt(page) + 12;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        log.debug("CHECK SEARCH RESULT PAGE : {} " + page);

        List<BookDTO> books = new BookDAO().sortBooks(sortWord, searchSelect, searchKeyword, genre, pageNum);

        request.setAttribute("page", pageNum + 12);

        request.setAttribute("books", books);

        request.setAttribute("searchSelect", searchSelect);

        request.setAttribute("sortWord", sortWord);

        request.setAttribute("searchKeyword", searchKeyword);

        response.setContentType("application/json");

        return "book/searchResult";
    }
}
