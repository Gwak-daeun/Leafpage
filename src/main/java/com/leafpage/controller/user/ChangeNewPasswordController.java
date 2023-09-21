package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.domain.user.Password;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangeNewPasswordController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Password newPassword = null;
        if(request.getParameter("newPassword")!=null) {
            newPassword = new Password(request.getParameter("newPassword"));
        }
        if(newPassword==null) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('오류가 발생했습니다.');");
            script.println("history.back();");
            script.println("</script>");
            script.close();
        } else {
            HttpSession session = request.getSession();
            int passwordChangeAccess = (Integer)session.getAttribute("passwordChangeAccess");
            String inputIdForNewPw = (String)session.getAttribute("inputIdForNewPw");
            String inputEmailForNewPw = (String)session.getAttribute("inputEmailForNewPw");
            String inputTelForNewPw = (String)session.getAttribute("inputTelForNewPw");
            if (passwordChangeAccess == 1 && inputIdForNewPw != null) {
                UserDAO userDAO = new UserDAO();
                int changePasswordSuccess = userDAO.changeNewPassword(newPassword.getUserPassword(), inputEmailForNewPw, inputTelForNewPw, inputIdForNewPw);
                if(changePasswordSuccess == 1) {
                    System.out.println("비밀번호 변경에 성공하였습니다.");
                    session.removeAttribute("passwordChangeAccess");
                    session.removeAttribute("inputIdForNewPw");
                    session.removeAttribute("inputEmailForNewPw");
                    session.removeAttribute("inputTelForNewPw");
                    return "index";
                }
            }
        }
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('비밀번호 변경에 실패했습니다.');");
        script.println("history.back();");
        script.println("</script>");
        script.close();
        return "index";
    }
}
