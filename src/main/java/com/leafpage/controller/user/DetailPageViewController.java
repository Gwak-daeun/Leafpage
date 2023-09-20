package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.LikeyDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class DetailPageViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long userNo = Long.parseLong(request.getParameter("userNo").trim());
        String isbn = request.getParameter("isbn").trim();

        LikeyDAO likeyDAO = new LikeyDAO();

        int checkLike = likeyDAO.checkLike(userNo, isbn);

        HttpSession session = request.getSession();

        session.setAttribute("heartSelect", checkLike);

        return "book/detailPage";
    }
}
