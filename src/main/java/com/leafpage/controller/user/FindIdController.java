package com.leafpage.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FindIdController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputEmail = null;
        String inputTel = null;

        if (request.getParameter("inputEmail") != null) {
            inputEmail = request.getParameter("inputEmail");
        }
        if (request.getParameter("inputTel") != null) {
            inputTel = request.getParameter("inputTel");
        }

        HttpSession session = request.getSession();
        if (inputEmail == null && inputTel == null) {
            session.setAttribute("msg", "[Error] 비정상적인 접근입니다.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "비정상적인 접근입니다.");
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
