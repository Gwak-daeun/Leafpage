package com.leafpage.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.domain.user.Email;
import com.leafpage.domain.user.Tel;
import com.leafpage.util.SHA256;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserInfoController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String passwordForUpdate = null;
        Tel telForUpdate = null;
        Email emailForUpdate = null;
        int updateInfo = 0;

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String currentTel = (String) session.getAttribute("userTel");
        String currentEmail = (String) session.getAttribute("userEmail");

        Map<String, Object> jsonResponse = new HashMap<>();

        UserDAO userDAO = UserDAO.getInstance();
        if (request.getParameter("passwordForUpdate") != null) {
            passwordForUpdate = SHA256.getSHA256(request.getParameter("passwordForUpdate"));
        }
        if (request.getParameter("inputUserTel") != null) {
            telForUpdate = new Tel(request.getParameter("inputUserTel"));
        }
        if (request.getParameter("inputUserEmail") != null) {
            emailForUpdate = new Email(request.getParameter("inputUserEmail"));
        }

        if (telForUpdate != null && emailForUpdate != null) {
            String newUserTel = telForUpdate.getUserTel();
            String newUserEmail = emailForUpdate.getUserEmail();

            if (!newUserTel.equals(currentTel) && isDuplicatedTel(newUserTel)) {
                System.out.println("이미 가입된 전화번호가 있습니다.");
                jsonResponse.put("duplicateTelError", "이미 가입된 전화번호가 있습니다.");
            } else if (!newUserEmail.equals(currentEmail) && isDuplicatedEmail(newUserEmail)) {
                System.out.println("이미 가입된 이메일이 있습니다.");
                jsonResponse.put("duplicateEmailError", "이미 가입된 이메일이 있습니다.");
            } else {
                updateInfo = userDAO.updateUserInfo(userId, newUserTel, newUserEmail, passwordForUpdate);
                if (updateInfo == 1) {
                    System.out.println("정보변경 완료. 이메일 재인증 필요.");
                    session.setAttribute("userEmailChecked", false);
                    session.setAttribute("userTel", newUserTel);
                    session.setAttribute("userEmail", newUserEmail);
                    session.setAttribute("msg", "정보변경이 완료되었습니다. 이메일 재인증이 필요합니다.");
                    jsonResponse.put("success", "정보변경이 완료되었습니다. 이메일 재인증이 필요합니다.");
                } else if (updateInfo == 0) {
                    System.out.println("비밀번호가 틀렸습니다.");
                    jsonResponse.put("passwordError", "비밀번호가 틀렸습니다.");
                }
            }
        } else {
            System.out.println("값이 오지 않았음");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다.");
        }
        String json = new ObjectMapper().writeValueAsString(jsonResponse);
        response.setContentType("application/json"); // JSON 형식으로 응답을 전달함을 설정
        PrintWriter out = response.getWriter();
        out.print(json);
        out.close();
        System.out.println(json);
        return "none";
    }

    private boolean isDuplicatedTel(String userTel) {
        return UserDAO.getInstance().findUserByEmail(userTel) == 1;
    }

    private boolean isDuplicatedEmail(String userEmail) {
        return UserDAO.getInstance().findUserByEmail(userEmail) == 1;
    }
}