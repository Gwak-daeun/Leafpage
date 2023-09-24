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
        if (request.getParameter("code") != null) {
            code = request.getParameter("code");
        }

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            userId = (String) session.getAttribute("inactiveIdForActive");
            System.out.println("check:" + userId);
        }

        UserDAO userDAO = new UserDAO();
        String userEmail = userDAO.getUserEmail(userId);
        boolean isRight = new SHA256().getSHA256(userEmail).equals(code);
        if (isRight) {
            userDAO.setUserEmailChecked(userId);
            if (session.getAttribute("inactiveIdForActive") != null) {
                int userInactive = userDAO.setUserStateInactive(userId, "일반회원");
                if (userInactive == 1) {
                    session.setAttribute("msg", "휴면상태가 정상적으로 해제되었습니다. 다시 로그인하여 주십시오.");
                    return "loginView.do";
                } else {
                    session.setAttribute("msg", "[Error] 휴면상태 해제 중 오류가 발생하였습니다.");
                    response.sendRedirect("indexInfo.do");
                }
            }
            session.setAttribute("userEmailChecked", userDAO.getUserEmailChecked(userId));
            return "successEmailCheckView.do";
        } else {
            return "failEmailCheckView.do";
        }
    }
}
