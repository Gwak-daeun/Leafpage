package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.domain.user.Email;
import com.leafpage.domain.user.Tel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserInfoController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Tel TelForUpdate = null;
        Email EmailForUpdate = null;
        int updateInfo = 0;

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        UserDAO userDAO = new UserDAO();
        if (request.getParameter("TelForUpdate") != null) {
            TelForUpdate = new Tel(request.getParameter("TelForUpdate"));
        }
        if (request.getParameter("EmailForUpdate") != null) {
            EmailForUpdate = new Email(request.getParameter("EmailForUpdate"));
        }
        if (TelForUpdate != null && EmailForUpdate != null) {

            if (!isDuplicatedTel(TelForUpdate.getUserTel()) && !isDuplicatedEmail(EmailForUpdate.getUserEmail())) {
                updateInfo = userDAO.updateUserInfo(userId, TelForUpdate.getUserTel(), EmailForUpdate.getUserEmail());
                if (updateInfo == 1) {
                    session.setAttribute("userEmailChecked", false);
                    System.out.println("정보변경 완료. 이메일 재인증 필요.");
                } else {
                    System.out.println("정보변경에 실패하였습니다.");
                }
            } else {
                System.out.println("중복된 전화번호나 이메일이 있습니다.");
            }
        }else {
            System.out.println("값이 오지 않았음");
        }
        return "index";
    }

    private boolean isDuplicatedTel(String userTel) {
        return new UserDAO().findUserByEmail(userTel) == 1;
    }

    private boolean isDuplicatedEmail(String userEmail) {
        return new UserDAO().findUserByEmail(userEmail) == 1;
    }
}