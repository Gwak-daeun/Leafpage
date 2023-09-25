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

public class InactiveController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        int userInactive = userDAO.setUserStateInactive(userId, "휴면회원");
        if (userInactive == 1) {
            session.invalidate();
            HttpSession newSession = request.getSession();
            newSession.setAttribute("msg", "휴면처리가 완료되어 로그아웃 됩니다.");
            response.sendRedirect("indexInfo.do");
        } else if (userInactive == 0) {
            session.setAttribute("msg", "[Error] 잘못된 접근입니다.");
            return "updateMyInfoView.do";
        } else {
            session.setAttribute("msg", "[Error] 데이터베이스 오류가 발생하였습니다.");
            response.sendRedirect("indexInfo.do");
        }
        return "none";
    }
}
