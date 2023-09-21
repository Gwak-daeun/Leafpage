package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.dto.UserDTO;
import com.leafpage.util.SHA256;

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
            }
            if(request.getParameter("userPassword") != null) {
                userPassword = request.getParameter("userPassword");
            }
            if(request.getParameter("userPasswordConfirm") != null) {
                userPasswordConfirm = request.getParameter("userPasswordConfirm");
            }
            if(request.getParameter("userEmail") != null) {
                userEmail = request.getParameter("userEmail");
            }
            if(request.getParameter("userTel") != null) {
                userTel = request.getParameter("userTel");
            }
            if(request.getParameter("userSecurityQuestion") != null) {
                userSecurityQuestion = request.getParameter("userSecurityQuestion");
            }
            if(request.getParameter("userSecurityAnswer") != null) {
                userSecurityAnswer = request.getParameter("userSecurityAnswer");
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


//new UserDTO(0, userId, SHA256.getSHA256(userPassword), userEmail, userTel, "일반회원", "회원", userSecurityQuestion, userSecurityAnswer, com.leafpage.util.SHA256.getSHA256(userEmail), false)
            UserDAO userDAO = new UserDAO();
            UserDTO userDTO = new UserDTO();

            if(isDuplicateId(userId)) {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('이미 가입된 아이디입니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
            }
            else if (isDuplicatedTel(userTel)) {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('이미 가입된 전화번호입니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
            }
            else if(isDuplicatedEmail(userEmail)) {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('이미 가입된 이메일입니다.');");
                script.println("history.back();");
                script.println("</script>");
                script.close();
            }
            else{
                userDTO.setUserNo(0);
                userDTO.setUserId(userId);
                userDTO.setUserPassword(userPassword);
                userDTO.setUserEmail(userEmail);
                userDTO.setUserTel(userTel);
                userDTO.setUserState("일반회원");
                userDTO.setUserRole("회원");
                userDTO.setUserSecurityQuestion(userSecurityQuestion);
                userDTO.setUserSecurityAnswer(userSecurityAnswer);
                userDTO.setUserEmailHash(SHA256.getSHA256(userEmail));
                userDTO.setUserEmailChecked(false);

                int result = userDAO.signup(userDTO);
                if (result == -1) {
                    PrintWriter script = response.getWriter();
                    script.println("<script>");
                    script.println("alert('회원가입에 실패하였습니다.');");
                    script.println("history.back();");
                    script.println("</script>");
                    script.close();
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", userId);  //가입 성공 시 바로 로그인상태로
                    return "sendEmail.do";
                }
            }
        } else {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('개인정보수집에 동의해주세요.');");
            script.println("history.back();");
            script.println("</script>");
            script.close();
        }
        return "signupView.do";
    }

    private boolean isDuplicateId(String userId) {
        return new UserDAO().findUserById(userId) == 1;
    }
    private boolean isDuplicatedTel(String userTel) {
        return new UserDAO().findUserByEmail(userTel) == 1;
    }
    private boolean isDuplicatedEmail(String userEmail) {
        return new UserDAO().findUserByEmail(userEmail) == 1;
    }
}