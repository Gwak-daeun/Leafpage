package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.dto.AdminBookListPageDTO;
import com.leafpage.dto.UserDTO;
import com.leafpage.util.ListPageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminUserListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int pageNum = 1;
        int amount = 10;

        // 페이지번호를 클릭하는 경우
        if (request.getParameter("pageNum") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        UserDAO dao = UserDAO.getInstance();

        List<UserDTO> userList = dao.userrentelList(pageNum, amount);
        int total = dao.getTotal();

        AdminBookListPageDTO pageDTO = new AdminBookListPageDTO();
        pageDTO.setPageNum(pageNum);
        pageDTO.setAmount(amount);
        pageDTO.setTotal(total);
        ListPageUtil pageUtil = new ListPageUtil();
        pageUtil.listpage(pageDTO);

        request.setAttribute("pageDTO", pageDTO);

        request.setAttribute("userList", userList);


        return "admin/admin-usermanagementrentallist";
    }
}
