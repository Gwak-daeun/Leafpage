package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
//import com.leafpage.dto.AdminBookListPageDTO;
import com.leafpage.dto.AdminBookListPageDTO;
import com.leafpage.dto.BookDTO;
import com.leafpage.util.BookListPageUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminBookListController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 첫 페이지 경우
        int pageNum = 1;
        int amount = 10;

        System.out.println(request.getParameter("pageNum"));
        // 페이지번호를 클릭하는 경우
        if(request.getParameter("pageNum") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }

        BookDAO dao = new BookDAO();
         // 전체게시글수
        System.out.println(pageNum);
        System.out.println(amount);
        List<BookDTO> bookList = dao.booklist(pageNum, amount);
        int total = dao.getTotal();

        AdminBookListPageDTO pageDTO = new AdminBookListPageDTO();
        pageDTO.setPageNum(pageNum);
        pageDTO.setAmount(amount);
        pageDTO.setTotal(total);
        BookListPageUtil pageUtil = new BookListPageUtil();
        pageUtil.booklistpage(pageDTO);




        // 3. 페이지네이션을 화면에 전달
        request.setAttribute("pageDTO", pageDTO);

        request.setAttribute("bookList" ,bookList);


        return "admin/admin-bookmanagement";
    }



}
