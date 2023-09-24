package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dao.UserDAO;
import com.leafpage.dto.AdminBookListPageDTO;
import com.leafpage.dto.BookDTO;
import com.leafpage.dto.UserDTO;
import com.leafpage.util.ListPageUtil;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminBookSearchListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int pageNum = 1;
        int amount = 10;
        if (request.getParameter("pageNum") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        String keyword = request.getParameter("keyword");
        HttpSession session = request.getSession();

        if (keyword == null || keyword.isEmpty() && session.getAttribute("keyword") != null) {
            keyword = (String) session.getAttribute("keyword");
        }

        if (keyword.isEmpty() && session.getAttribute("keyword") == null) {
            return "/booklistView.do";
        } else {
            session.setAttribute("keyword", keyword);
        }


        BookDAO dao = new BookDAO();
        // 전체게시글수
        List<BookDTO> bookList = dao.booksearchlist(pageNum, amount, keyword);
        int total = dao.getsearchTotal(keyword);

        AdminBookListPageDTO pageDTO = new AdminBookListPageDTO();
        pageDTO.setPageNum(pageNum);
        pageDTO.setAmount(amount);
        pageDTO.setTotal(total);
        ListPageUtil pageUtil = new ListPageUtil();
        pageUtil.listpage(pageDTO);


        // 3. 페이지네이션을 화면에 전달
        request.setAttribute("pageDTO", pageDTO);

        request.setAttribute("bookList", bookList);


        return "admin/admin-bookmanagementsearchlist";
    }
}
