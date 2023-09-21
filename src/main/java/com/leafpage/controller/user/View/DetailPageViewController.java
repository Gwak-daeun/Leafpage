package com.leafpage.controller.user.View;

import com.leafpage.controller.Controller;
import com.leafpage.dao.LeeLikeyDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DetailPageViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long userNo = Long.parseLong(request.getParameter("userNo").trim());
        String isbn = request.getParameter("isbn").trim();

        LeeLikeyDAO likeyDAO = new LeeLikeyDAO();

        int checkLike = likeyDAO.checkLike(userNo, isbn);

        HttpSession session = request.getSession();

        session.setAttribute("heartSelect", checkLike);

        return "book/detailPage";
    }
}
