package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dao.LikeyDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BookYController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("START SAVE Y");

        String strModalY = request.getParameter("modalY");

        String strModalWidth = request.getParameter("modalWidth");

        int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));

        double doubleModalY = Double.parseDouble(strModalY);
        int modalY = (int) doubleModalY;

        int modalWidth = (int) Double.parseDouble(strModalWidth);

        int result = new BookDAO().saveBookScrollY(modalY, modalWidth, rentalNo);

        return "mypageInfo.do";
    }
}
