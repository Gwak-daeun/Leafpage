package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 로그인 인증 처리를 담당하는 컨트롤러
public class LoginController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("로그인 처리");

        String userId = null;
        String userPassword = null;
        HttpSession session = request.getSession();

        if (request.getParameter("userId") != null) {
            userId = request.getParameter("userId");
        }
        if (request.getParameter("userPassword") != null) {
            userPassword = request.getParameter("userPassword");
        }
        if (userId == null || userPassword == null) {
            session.setAttribute("msg", "[Error] 값이 입력되지 않았습니다.");
            sendRedirect(response, "loginView.do");
        }

        UserDAO userDAO = UserDAO.getInstance();
        int result = userDAO.login(userId, userPassword);
        boolean userEmailChecked = userDAO.getUserEmailChecked(userId);
        Long userNo = userDAO.getUserNo(userId);

        return handleLoginResult(result, session, userId, userEmailChecked, userNo, response);
    }

    private String handleLoginResult(int result, HttpSession session, String userId, boolean userEmailChecked, Long userNo, HttpServletResponse response) {
        switch (result) {
            case -3:
                return handleDatabaseError(session);
            case -2:
                return handleNonExistentUserId(session);
            case -1:
                return handleIncorrectPassword(session);
            case 0:
                handleAdminLogin(session, userId, userNo, response);
                break;
            case 1:
                handleUserLogin(session, userId, userEmailChecked, userNo, response);
                break;
            case 2:
                handleInactiveUserLogin(session, userId, userEmailChecked, userNo, response);
                break;
            case 3:
                handleBlacklistedUserLogin(session, response);
                break;
            case 4:
                handleWithdrawnUserLogin(session, response);
                break;
            default:
                handleUnknownError(session, response);
                break;
        }
        return "none";
    }

    private String handleDatabaseError(HttpSession session) {
        session.setAttribute("msg", "[Error] 데이터베이스 오류가 발생했습니다.");
        return "loginView.do";
    }

    private String handleNonExistentUserId(HttpSession session) {
        session.setAttribute("msg", "존재하지 않는 아이디입니다.");
        return "loginView.do";
    }

    private String handleIncorrectPassword(HttpSession session) {
        session.setAttribute("msg", "비밀번호가 틀립니다.");
        return "loginView.do";
    }

    private void handleAdminLogin(HttpSession session, String userId, Long userNo, HttpServletResponse response) {
        session.setAttribute("msg", "관리자 계정입니다. 관리자 페이지로 이동합니다.");
        session.setAttribute("userId", userId);
        session.setAttribute("userEmailChecked", true);
        session.setAttribute("userNo", userNo);
        sendRedirect(response, "booklistView.do");
    }

    private void handleUserLogin(HttpSession session, String userId, boolean userEmailChecked, Long userNo, HttpServletResponse response) {
        session.setAttribute("msg", "로그인에 성공하였습니다.");
        session.setAttribute("userId", userId);
        session.setAttribute("userEmailChecked", userEmailChecked);
        session.setAttribute("userNo", userNo);
        sendRedirect(response, "indexInfo.do");
    }

    private void handleInactiveUserLogin(HttpSession session, String userId, boolean userEmailChecked, Long userNo, HttpServletResponse response) {
        int userInactive = UserDAO.getInstance().setUserState(userId, "휴면회원");
        if(userInactive == 1) {
            session.setAttribute("inactiveUserId", userId);
            session.setAttribute("userId", userId);
            session.setAttribute("userEmailChecked", userEmailChecked);
            session.setAttribute("userNo", userNo);
            session.setAttribute("msg", "휴면회원입니다. 이메일 인증을 완료하시면 서비스 이용이 가능합니다.");
            sendRedirect(response, "emailResendView.do");
        } else {
            session.setAttribute("msg", "[Error] 알 수 없는 오류가 발생했습니다. 메인페이지로 이동합니다.");
            sendRedirect(response, "indexInfo.do");
        }
    }

    private void handleBlacklistedUserLogin(HttpSession session, HttpServletResponse response) {
        session.setAttribute("msg", "서비스를 이용하실 수 없습니다. 자세한 내용은 운영자에게 문의하십시오.");
        sendRedirect(response, "indexInfo.do");
    }

    private void handleWithdrawnUserLogin(HttpSession session, HttpServletResponse response) {
        session.setAttribute("msg", "탈퇴하신 회원입니다. 재가입을 환영합니다.");
        sendRedirect(response, "signupView.do");
    }

    private void handleUnknownError(HttpSession session, HttpServletResponse response) {
        session.setAttribute("msg", "[Error] 알 수 없는 오류가 발생했습니다.");
        sendRedirect(response, "signupView.do");
    }

    private void sendRedirect(HttpServletResponse response, String location) {
        try {
            response.sendRedirect(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}