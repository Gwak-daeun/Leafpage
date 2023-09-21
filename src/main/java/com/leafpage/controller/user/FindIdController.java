package com.leafpage.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FindIdController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputEmail = null;
        String inputTel = null;

        if(request.getParameter("inputEmail") != null) {
            inputEmail = request.getParameter("inputEmail");
        }
        if(request.getParameter("inputTel") != null) {
            inputTel = request.getParameter("inputTel");
        }

        if(inputEmail == null && inputTel == null) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('이메일이나 전화번호를 입력해주세요.');");
            script.println("history.back();");
            script.println("</script>");
            script.close();
        } else {
            String foundUserId = new UserDAO().findIdByEmailOrTel(inputEmail, inputTel);
            System.out.println("##" + foundUserId);
            response.setContentType("application/json"); // JSON 형식으로 응답을 전달함을 설정
            PrintWriter out = response.getWriter();
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("foundUserId", foundUserId);
            String json = new ObjectMapper().writeValueAsString(jsonResponse);
            out.print(json);
            out.close();
        }
        return null;
    }
}
