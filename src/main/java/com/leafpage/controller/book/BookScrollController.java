package com.leafpage.controller.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

        int pageNum = 0;

        if (request.getParameter("page") != null) {
            page = request.getParameter("page");

            try {
                pageNum = Integer.parseInt(page);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        log.debug("CHECK SCOLL PAGE : {} " + pageNum);

        List<BookDTO> books = BookDAO.getInstance().sortBooks(sortWord, searchSelect, searchKeyword, genre, pageNum);

        request.setAttribute("books", books);

        request.setAttribute("searchSelect", searchSelect);

        request.setAttribute("sortWord", sortWord);

        request.setAttribute("page", pageNum + 10);

        response.setContentType("application/json");

        response.setContentType("application/json"); // JSON 형식으로 응답을 전달함을 설정
        PrintWriter out = response.getWriter();
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("books", books);
        String json = new ObjectMapper().writeValueAsString(jsonResponse);
        out.print(json);
        out.close();

        return null;
    }
}
