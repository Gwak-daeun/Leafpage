package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// 로그인 인증 처리를 담당하는 컨트롤러
public class LoginController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("로그인 처리");

        String userId = null;
        String userPassword = null;
        if (request.getParameter("userId") != null) {
            userId = request.getParameter("userId");
        }
        if (request.getParameter("userPassword") != null) {
            userPassword = request.getParameter("userPassword");
        }
        if (userId == null || userPassword == null) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('입력이 안 된 사항이 있습니다.');");
            script.println("history.back();");
            script.println("</script>");
            script.close();
        }

        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();
        int result = userDAO.login(userId, userPassword);
        boolean userEmailChecked = userDAO.getUserEmailChecked(userId);
        PrintWriter script = response.getWriter();
        switch (result) {
            case -3:   //[-3]데이터베이스 오류
                script.println("<script>");
                script.println("alert('데이터베이스 오류가 발생했습니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
                break;

            case -2:   // [-2]아이디 없음
                script.println("<script>");
                script.println("alert('존재하지 않는 아이디입니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
                return "index";

            case -1:   // [-1]비밀번호 틀림
                script.println("<script>");
                script.println("alert('비밀번호가 틀립니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
                break;

            case 0:  // [0]관리자 로그인
                session.setAttribute("userId", userId);
                return "booklistView.do";

            case 1:  // [1]일반회원 로그인
                session.setAttribute("userId", userId);
                session.setAttribute("userEmailChecked", userEmailChecked);
                return "index";

            case 2:  //[2]휴면회원 로그인
                script.println("<script>");
                script.println("alert('휴면회원입니다.');");
                script.println("</script>");
                script.close();
                break;

            case 3:  //[3]블랙회원 로그인
                script.println("<script>");
                script.println("alert('서비스를 이용하실 수 없습니다. 자세한 내용은 운영자에게 문의하십시오.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
                break;

            case 4:  //[4]탈퇴회원 로그인
                script.println("<script>");
                script.println("alert('탈퇴한 회원입니다. 재가입을 환영합니다.');");
                script.println("location.href='signup.do'");
                script.println("</script>");
                script.close();
                break;

            default:
                script.println("<script>");
                script.println("alert('알 수 없는 오류가 발생했습니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
                break;
        }
        return null;
    }
}