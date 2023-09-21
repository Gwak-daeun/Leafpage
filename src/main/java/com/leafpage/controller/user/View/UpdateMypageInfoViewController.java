package com.leafpage.controller.user.View;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateMypageInfoViewController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("내정보수정으로 이동");
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        session.setAttribute("userTel", userDAO.getUserTel(userId));
        session.setAttribute("userEmail", userDAO.getUserEmail(userId));
        return "user/updateMyInfo";
    }
}
