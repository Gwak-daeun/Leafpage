package com.leafpage.controller.book;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BookYController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("START SAVE Y");

        String strModalY = request.getParameter("modalY");

        String strModalWidth = request.getParameter("modalWidth");

        int modalY = Integer.parseInt(strModalY);

        int modalWidth = (int) Double.parseDouble(strModalWidth);

        int rental_no = 5; //나중에 파라미터 받아오는걸로 고쳐야 함

        int result = new BookDAO().saveBookScrollY(modalY, modalWidth, rental_no);

        return "/mypageInfo.do";
    }
}
