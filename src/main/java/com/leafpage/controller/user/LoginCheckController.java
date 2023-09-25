package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginCheckController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loginUserId = null;
        String loginUserPassword = null;

        if (request.getParameter("loginUserId") != null) {
            loginUserId = request.getParameter("loginUserId");
        }

        if (request.getParameter("loginUserPassword") != null) {
            loginUserPassword = request.getParameter("loginUserPassword");
        }

        if (loginUserId != null && loginUserPassword != null) {
            try {
                UserDAO userDAO = UserDAO.getInstance();
                int result = userDAO.login(loginUserId, loginUserPassword);
                System.out.println(result);
                PrintWriter out = response.getWriter();
                out.print(result);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "none";
    }
}
