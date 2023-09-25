package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.util.SHA256;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class CheckEmailController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDAO userDAO = new UserDAO();
        String code = request.getParameter("code");
        String userId = request.getParameter("userId");
        String inactiveUserId = request.getParameter("inactiveUserId");
        String userEmailHash = userDAO.getUserEmailHash(userId);
        boolean userEmailChecked = userDAO.getUserEmailChecked(userId);
        boolean isCodeCorrect = isCodeCorrect(code, userEmailHash);

        HttpSession session = request.getSession();
        //이미 이메일 인증을 완료한 유저가 다시 들어왔을 때
        if(userEmailChecked) {
            session.setAttribute("msg", "이미 이메일 인증이 완료된 회원입니다.");
            response.sendRedirect("indexInfo.do");
            return "none";
        }

        if (isCodeCorrect) {
            handleCorrectCode(userId, inactiveUserId, session, response);
            return "none";
        } else {
            return "failEmailCheckView.do";
        }
    }


    private void handleCorrectCode(String userId, String inactiveUserId, HttpSession session, HttpServletResponse response) throws IOException {
        UserDAO userDAO = new UserDAO();
        //다른 세션에서 인증을 시도했을 시를 위한 로그인
        session.setAttribute("userId", userId);
        session.setAttribute("userNo", userDAO.getUserNo(userId));

        if(userDAO.setUserEmailChecked(userId)) {
            session.setAttribute("userEmailChecked", userDAO.getUserEmailChecked(userId));
            sendMessageForActive(inactiveUserId, userId, session);
        }
        response.sendRedirect("successEmailCheckView.do");
    }

    private void sendMessageForActive (String inactiveUserId, String userId, HttpSession session) {
        if (inactiveUserId != null) {
            int userInactive = new UserDAO().setUserState(userId, "일반회원");
            if (userInactive == 1) {
                session.setAttribute("msg", "휴면상태가 정상적으로 해제되었습니다.");
            } else {
                session.setAttribute("msg", "[Error] 휴면상태 해제 중 오류가 발생하였습니다.");
            }
        } else {
            session.setAttribute("msg", "이메일 인증이 완료되었습니다.");
        }
    }

    private boolean isCodeCorrect(String code, String userEmailHash) {
        System.out.println("code:"+code);
        System.out.println("userEmailHash:"+userEmailHash);
        return userEmailHash.equals(code);
    }

}