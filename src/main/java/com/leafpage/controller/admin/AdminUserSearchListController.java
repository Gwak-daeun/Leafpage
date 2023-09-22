package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.dto.AdminBookListPageDTO;
import com.leafpage.dto.UserDTO;
import com.leafpage.util.ListPageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminUserSearchListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int pageNum = 1;
        int amount = 10;

        // 페이지번호를 클릭하는 경우
        if(request.getParameter("pageNum") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }

        String keyword = request.getParameter("keyword");
        HttpSession session = request.getSession();

        if(keyword == null || keyword.isEmpty() && session.getAttribute("keyword") != null){
            keyword = (String) session.getAttribute("keyword");
        }

        if(keyword.isEmpty() && session.getAttribute("keyword") == null){
            return "userlistview.do";
        }

        else {
            session.setAttribute("keyword", keyword);
        }

        UserDAO dao =new UserDAO();

        List<UserDTO> userList = dao.userSearchList(pageNum, amount, keyword);
        int total = dao.getSearchTotal(keyword);

        AdminBookListPageDTO pageDTO = new AdminBookListPageDTO();
        pageDTO.setPageNum(pageNum);
        pageDTO.setAmount(amount);
        pageDTO.setTotal(total);
        ListPageUtil pageUtil = new ListPageUtil();
        pageUtil.listpage(pageDTO);

        request.setAttribute("pageDTO", pageDTO);

        request.setAttribute("userList" ,userList);


        return "admin/admin-usermanagementsearchlist";
    }
}
