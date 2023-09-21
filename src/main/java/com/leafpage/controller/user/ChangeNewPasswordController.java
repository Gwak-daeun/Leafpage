package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.domain.user.Password;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangeNewPasswordController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("changeNewPasswordController진입 새로운비밀번호:"+request.getParameter("newPassword"));
        int changePasswordSuccess = 0;
        Password newPassword = null;
        if(request.getParameter("newPassword")!=null) {
            newPassword = new Password(request.getParameter("newPassword"));
        }

        if(newPassword != null) {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            if (userId != null) {
                UserDAO userDAO = new UserDAO();
                changePasswordSuccess = userDAO.changeNewPassword(newPassword.getUserPassword(), userId);
            }
            if (userId == null) {
                int passwordChangeAccess = (Integer) session.getAttribute("passwordChangeAccess");
                String inputIdForNewPw = (String) session.getAttribute("inputIdForNewPw");
                if (passwordChangeAccess == 1) {
                    UserDAO userDAO = new UserDAO();
                    changePasswordSuccess = userDAO.changeNewPassword(newPassword.getUserPassword(), inputIdForNewPw);
                    session.removeAttribute("passwordChangeAccess");
                    session.removeAttribute("inputIdForNewPw");
                }
            }

            if (changePasswordSuccess == 1) {
                System.out.println("비밀번호 변경에 성공하였습니다.");
            }
            if (changePasswordSuccess == 0) {
                System.out.println("비밀번호 변경에 실패하였습니다.");
            }
        } else {
            System.out.println("오류가 발생했습니다.");
        }
        return "index";
    }
}

