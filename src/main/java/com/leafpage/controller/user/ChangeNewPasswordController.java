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
        System.out.println("changeNewPasswordController진입");
        System.out.println("새로운비밀번호:" + request.getParameter("newPassword"));

        Password newPassword = null;
        if (request.getParameter("newPassword") != null) {
            newPassword = new Password(request.getParameter("newPassword"));
        }

        handlePasswordChange(newPassword, request);
        return "findPwView.do";
    }

    private void handlePasswordChange(Password newPassword, HttpServletRequest request) {
        if(newPassword != null) {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            int changePasswordSuccess = getChangePasswordSuccess(userId, newPassword, session);
            sendMessage(changePasswordSuccess, session);
        } else {
            System.out.println("newPassword==null 임");
        }
    }

    private int getChangePasswordSuccess(String userId, Password newPassword, HttpSession session) {
        int changePasswordSuccess = 0;
        UserDAO userDAO = UserDAO.getInstance();
        if (userId != null) {
            changePasswordSuccess = userDAO.changeNewPassword(newPassword.getUserPassword(), userId);
        }
        if (userId == null) {
            int passwordChangeAccess = (Integer) session.getAttribute("passwordChangeAccess");
            String inputIdForNewPw = (String) session.getAttribute("inputIdForNewPw");
            if (passwordChangeAccess == 1) {
                changePasswordSuccess = userDAO.changeNewPassword(newPassword.getUserPassword(), inputIdForNewPw);
                session.removeAttribute("passwordChangeAccess");
                session.removeAttribute("inputIdForNewPw");
            }
        }
        return changePasswordSuccess;
    }

    private void sendMessage(int changePasswordSuccess, HttpSession session) {
        if (changePasswordSuccess == 1) {
            System.out.println("비밀번호 변경에 성공하였습니다.");
            session.setAttribute("msg", "비밀번호 변경에 성공하였습니다.");
        }
        if (changePasswordSuccess == 0) {
            System.out.println("비밀번호 변경에 실패하였습니다.");
            session.setAttribute("msg", "[Error] 유효하지 않은 접근입니다.");
        }
    }
}

