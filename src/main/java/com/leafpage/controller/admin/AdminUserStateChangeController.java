package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUserStateChangeController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDAO dao = new UserDAO();
        UserDTO dto = new UserDTO();
        String userId = request.getParameter("userId");
        String state = request.getParameter("state");
        dto.setUserId(userId);

        if (state.equals("black")) {
            state = "블랙회원";
        }
        if (state.equals("dormant")) {
            state = "휴면회원";
        }
        if (state.equals("General")) {
            state = "일반회원";
        }

        if (state.equals("delete")) {
            dao.deletesearchUser(dto);
        } else {
            dto.setUserState(state);
            dao.stateUpDate(dto);
        }


        return "/userlistview.do";
    }
}
