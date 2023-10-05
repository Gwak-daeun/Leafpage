package com.leafpage.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//로그인은 했지만 이메일인증을 하지 않은 경우
@WebFilter(urlPatterns = {
        "/booklistView.do", "/getBook.do", "/books/edit.do", "/bookupload.do",
        "/remove.do", "/edit.do", "/adminbooksearch.do", "/adminusersearch.do",
        "/userlistview.do", "/userlistsignupView.do", "/userstatechange.do", "/LikeHeart.do",
        "/makeReview.do",  "/removeReview.do", "/saveUserBookY.do", "/rentBook.do", "/returnBook.do"
})
public class EmailCheckedAuthenticationFilter extends HttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        boolean userEmailChecked = (Boolean) session.getAttribute("userEmailChecked");

        System.out.println(userEmailChecked);

        //이메일 인증을 하지 않은 사용자가 서비스에 접근했을 경우
        if (!userEmailChecked && userId != null) {
            session.setAttribute("msg", "서비스를 이용하시려면 이메일인증을 해주세요.");
            res.sendRedirect("emailResendView.do");
        } else {
            chain.doFilter(request, response);
        }
    }
}