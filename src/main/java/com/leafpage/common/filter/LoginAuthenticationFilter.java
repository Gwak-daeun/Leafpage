package com.leafpage.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {  "/withdrawal.do", "/mypageInfo.do", "/Logout.do",
        "/updateMyInfoView.do", "/booklistView.do", "/getBook.do",
        "/books/edit.do", "/bookupload.do", "/remove.do",
        "/edit.do", "/adminbooksearch.do", "/adminusersearch.do",
        "/userlistview.do", "/userlistsignupView.do", "/userstatechange.do",
        "/removeReview.do", "/makeReview.do", "/LikeHeart.do",
        "/saveUserBookY.do", "/rentBook.do", "/returnBook.do" })
public class LoginAuthenticationFilter extends HttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            //로그인을 하지 않은 사용자가 로그인을 해야만 이용할 수 있는 서비스에 접근했을 경우
            session.setAttribute("msg", "로그인 후 이용하실 수 있습니다.");
            res.sendRedirect("loginView.do");
        } else {
            // 나머지 경우에는 필터 체인 계속 진행
            chain.doFilter(request, response);
        }
    }
}