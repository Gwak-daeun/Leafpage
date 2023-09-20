package com.leafpage.controller.user;


import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.domain.user.Id;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DuplicateIdCheckController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("signupUserId") != null) {
            try {
                String userId = request.getParameter("signupUserId");
                int isDuplicate = new UserDAO().findUserById(userId);
                System.out.println(isDuplicate);
                PrintWriter out = response.getWriter();
                out.print(isDuplicate);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
