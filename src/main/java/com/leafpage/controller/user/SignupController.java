package com.leafpage.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.dto.UserDTO;
import com.leafpage.util.SHA256;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SignupController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("회원가입 처리");

        String userId = null;
        String userPassword = null;
        String userEmail = null;
        String userTel = null;
        String userSecurityQuestion = null;
        String userSecurityAnswer = null;

        if (request.getParameter("userId") != null) {
            userId = request.getParameter("userId");
        }
        if (request.getParameter("userPassword") != null) {
            userPassword = request.getParameter("userPassword");
        }
        if (request.getParameter("userEmail") != null) {
            userEmail = request.getParameter("userEmail");
        }
        if (request.getParameter("userTel") != null) {
            userTel = request.getParameter("userTel");
        }
        if (request.getParameter("userSecurityQuestion") != null) {
            userSecurityQuestion = request.getParameter("userSecurityQuestion");
        }
        if (request.getParameter("userSecurityAnswer") != null) {
            userSecurityAnswer = request.getParameter("userSecurityAnswer");
        }

        HttpSession session = request.getSession();
        response.setContentType("application/json"); // JSON 형식으로 응답을 전달함을 설정
        PrintWriter out = response.getWriter();
        Map<String, Object> jsonResponse = new HashMap<>();

        if (userId == null || userPassword == null  || userEmail == null || userTel == null
                || userSecurityQuestion == null || userSecurityAnswer == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "비정상적인 접근입니다.");
            return "signupView.do";
        }

        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO();


        if (isDuplicateId(userId)) {
            jsonResponse.put("duplicateIdError", "이미 가입된 아이디가 있습니다.");
        } else if (isDuplicatedTel(userTel)) {
            jsonResponse.put("duplicateTelError", "이미 가입된 전화번호가 있습니다.");
        } else if (isDuplicatedEmail(userEmail)) {
            jsonResponse.put("duplicateEmailError", "이미 가입된 이메일이 있습니다.");
        } else {
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
                jsonResponse.put("failError", "회원가입에 실패했습니다.");
            } else {
                session.setAttribute("msg", "회원가입에 성공했습니다. 이메일 인증을 완료해주세요.");
                session.setAttribute("userId", userId);  //가입 성공 시 바로 로그인상태로
                session.setAttribute("userEmailChecked", false);
                session.setAttribute("userNo", userDTO.getUserNo());
                jsonResponse.put("success", "회원가입에 성공했습니다.");
            }
        }

        String json = new ObjectMapper().writeValueAsString(jsonResponse);
        out.print(json);
        out.close();
        System.out.println(json);
        return null;
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