package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FindPwController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputId = null;
        String inputEmail = null;
        String inputTel = null;
        String selectQuestion = null;
        String inputAnswer = null;

        if(request.getParameter("inputId") != null) {
            inputId = request.getParameter("inputId");
        }
        if(request.getParameter("inputEmail") != null) {
            inputEmail = request.getParameter("inputEmail");
        }
        if(request.getParameter("inputTel") != null) {
            inputTel = request.getParameter("inputTel");
        }
        if(request.getParameter("selectQuestion") != null) {
            selectQuestion = request.getParameter("selectQuestion");
        }
        if(request.getParameter("inputAnswer") != null) {
            inputAnswer = request.getParameter("inputAnswer");
        }

//findPwByEmailOrTel(String inputId, String inputEmail, String inputTel, String selectQuestion, String inputAnswer)

        if(inputId == null || selectQuestion == null || inputAnswer == null) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('입력되지 않은 값이 있습니다.');");
            script.println("history.back();");
            script.println("</script>");
            script.close();
        } else if (inputEmail == null && inputTel == null) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('이메일이나 전화번호를 입력해주세요.');");
            script.println("history.back();");
            script.println("</script>");
            script.close();
        } else {
            int passwordChangeAccess = new UserDAO().findPwByEmailOrTel(inputId, inputEmail, inputTel, selectQuestion, inputAnswer);
            PrintWriter out = response.getWriter();
            out.print(passwordChangeAccess);
            out.close();
        }
        return null;
    }
}
