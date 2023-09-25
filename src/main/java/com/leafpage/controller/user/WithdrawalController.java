package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.util.SHA256;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class WithdrawalController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String passwordForWithdrawal = null;
        if (request.getParameter("checkPassword") != null) {
            passwordForWithdrawal = SHA256.getSHA256(request.getParameter("checkPassword"));
        }
        if (passwordForWithdrawal != null) {
            UserDAO userDAO = UserDAO.getInstance();
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            int userWithdrawal = userDAO.setUserStateWithdrawal(userId, passwordForWithdrawal, "탈퇴회원");
            PrintWriter out = response.getWriter();
            out.print(userWithdrawal);
            out.close();
        }
        return "none";
    }
}
