package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.util.SHA256;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckEmailController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = null;
        if(request.getParameter("code") != null) {
            code = request.getParameter("code");
        }

        HttpSession session = request.getSession();
        String userId  = (String)session.getAttribute("userId");

        UserDAO userDAO = new UserDAO();
        String userEmail = userDAO.getUserEmail(userId);
        boolean isRight = new SHA256().getSHA256(userEmail).equals(code);
        if (isRight) {
            userDAO.setUserEmailChecked(userId);
            session.setAttribute("userEmailChecked", userDAO.getUserEmailChecked(userId));
            return "successEmailCheckView.do";
        } else {
            return "failEmailCheckView.do";
        }
    }
}
