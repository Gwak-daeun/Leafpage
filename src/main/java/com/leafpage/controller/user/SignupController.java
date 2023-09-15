package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SignupController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("회원가입 처리");

        String userId = null;
        String userPassword = null;
        String userPasswordConfirm = null;
        String userEmail = null;
        String userTel = null;
        String userSecurityQuestion = null;
        String userSecurityAnswer = null;

        if(request.getParameter("userConsent") != null) {

            if(request.getParameter("userId") != null) {
                userId = request.getParameter("userId");
                System.out.println(userId);
            }
            if(request.getParameter("userPassword") != null) {
                userPassword = request.getParameter("userPassword");
                System.out.println(userPassword);
            }
            if(request.getParameter("userPasswordConfirm") != null) {
                userPasswordConfirm = request.getParameter("userPasswordConfirm");
                System.out.println(userPasswordConfirm);
            }
            if(request.getParameter("userEmail") != null) {
                userEmail = request.getParameter("userEmail");
                System.out.println(userEmail);
            }
            if(request.getParameter("userTel") != null) {
                userTel = request.getParameter("userTel");
                System.out.println(userTel);
            }
            if(request.getParameter("userSecurityQuestion") != null) {
                userSecurityQuestion = request.getParameter("userSecurityQuestion");
                System.out.println(userSecurityQuestion);
            }
            if(request.getParameter("userSecurityAnswer") != null) {
                userSecurityAnswer = request.getParameter("userSecurityAnswer");
                System.out.println(userSecurityAnswer);
            }

            if(userId == null || userPassword == null || userPasswordConfirm == null
                    || userEmail == null || userTel == null || userSecurityQuestion == null
                    || userSecurityAnswer == null) {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('입력이 안 된 사항이 있습니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
            }

            UserDAO userDAO = new UserDAO();
            int result = userDAO.signup(new UserDTO(0, userId, userPassword, userEmail, userTel, "일반회원", "회원", userSecurityQuestion, userSecurityAnswer));
            if (result == -1) {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('이미 존재하는 아이디입니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);  //가입 성공 시 바로 로그인상태로
                return "index";
            }

        } else {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('개인정보수집에 동의해주세요.');");
            script.println("history.back();");
            script.println("</script>");
            script.close();
        }
        return "index";
    }
}