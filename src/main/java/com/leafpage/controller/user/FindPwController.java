package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class FindPwController implements Controller {
    static final String PASSWORD_CHANGE_ACCESS_BUTTON = "<br><a id='change_pw_btn' data-bs-toggle='modal' data-bs-target='#newPasswordModal' class='btn btn-success mx-1 mt-2'>비밀번호 변경</a>";

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputId = null;
        String inputEmail = null;
        String inputTel = null;
        String selectQuestion = null;
        String inputAnswer = null;

        if (request.getParameter("inputId") != null) {
            inputId = request.getParameter("inputId");
        }
        if (request.getParameter("inputEmail") != null) {
            inputEmail = request.getParameter("inputEmail");
        }
        if (request.getParameter("inputTel") != null) {
            inputTel = request.getParameter("inputTel");
        }
        if (request.getParameter("selectQuestion") != null) {
            selectQuestion = request.getParameter("selectQuestion");
        }
        if (request.getParameter("inputAnswer") != null) {
            inputAnswer = request.getParameter("inputAnswer");
        }

        HttpSession session = request.getSession();

        if (inputId == null || selectQuestion == null || inputAnswer == null) {
            session.setAttribute("msg", "[Error] 정상적으로 입력되지 않은 값이 있습니다.");
            return "findPwView.do";
        } else if (inputEmail == null && inputTel == null) {
            session.setAttribute("msg", "[Error] 이메일이나 전화번호가 정상적으로 입력되지 않았습니다.");
            return "findPwView.do";
        } else {
            int passwordChangeAccess = new UserDAO().findPwByEmailOrTel(inputId, inputEmail, inputTel, selectQuestion, inputAnswer);
            if (passwordChangeAccess == 1) {
                session.setAttribute("passwordChangeAccess", passwordChangeAccess);
                session.setAttribute("inputIdForNewPw", inputId);
                PrintWriter out = response.getWriter();
                out.print(PASSWORD_CHANGE_ACCESS_BUTTON);
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.print(passwordChangeAccess);
                out.close();
            }
        }
        return "none";
    }

}
