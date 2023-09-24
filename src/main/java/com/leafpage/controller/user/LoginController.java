package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
            session.setAttribute("msg", "입력이 안 된 사항이 있습니다.");
            return "loginView.do";
        }

        UserDAO userDAO = new UserDAO();
        int result = userDAO.login(userId, userPassword);
        boolean userEmailChecked = userDAO.getUserEmailChecked(userId);
        int userNo = userDAO.getUserNo(userId);

        switch (result) {
            case -3:   //[-3]데이터베이스 오류
                session.setAttribute("msg", "[Error] 데이터베이스 오류가 발생했습니다.");
                return "loginView.do";

            case -2:   // [-2]아이디 없음
                session.setAttribute("msg", "존재하지 않는 아이디입니다.");
                return "loginView.do";

            case -1:   // [-1]비밀번호 틀림
                session.setAttribute("msg", "비밀번호가 틀립니다.");
                return "loginView.do";

            case 0:  // [0]관리자 로그인
                session.setAttribute("msg", "관리자계정으로 확인됩니다. 관리자 페이지로 이동합니다.");
                session.setAttribute("userId", userId);
                session.setAttribute("userEmailChecked", true);
                session.setAttribute("userNo", userNo);
                return "booklistView.do";

            case 1:  // [1]일반회원 로그인
                session.setAttribute("msg", "로그인에 성공하였습니다.");
                session.setAttribute("userId", userId);
                session.setAttribute("userEmailChecked", userEmailChecked);
                session.setAttribute("userNo", userNo);
                response.sendRedirect("indexInfo.do");
                break;

            case 2:  //[2]휴면회원 로그인
                session.setAttribute("inactiveIdForActive", userId);
                session.setAttribute("msg", "휴면회원입니다. 다시 이메일 인증을 수행하여 주세요.");
                response.sendRedirect("indexInfo.do");
                break;

            case 3:  //[3]블랙회원 로그인
                session.setAttribute("msg", "서비스를 이용하실 수 없습니다. 자세한 내용은 운영자에게 문의하십시오.");
                response.sendRedirect("indexInfo.do");
                break;

            case 4:  //[4]탈퇴회원 로그인
                session.setAttribute("msg", "탈퇴하신 회원입니다. 재가입을 환영합니다.");
                return "signup.do";

            default:
                session.setAttribute("msg", "[Error] 알 수 없는 오류가 발생했습니다.");
                return "loginView.do";
        }
        return "none";
    }
}