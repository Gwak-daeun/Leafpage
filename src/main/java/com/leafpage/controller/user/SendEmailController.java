package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;
import com.leafpage.util.SHA256;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class SendEmailController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("SendEmailController진입");

        HttpSession session = request.getSession();
        UserDAO userDAO = UserDAO.getInstance();
        String userId = null;
        String inactiveUserId = null;

        //일반+휴면회원이라면
        if(session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
        }
        //휴면회원이라면
        if(session.getAttribute("inactiveUserId") != null) {
            inactiveUserId = (String) session.getAttribute("inactiveUserId");
        }

        boolean emailChecked = userDAO.getUserEmailChecked(userId);
        if (emailChecked) {
            session.setAttribute("msg", "이미 이메일 인증이 완료되었습니다. 정상적인 서비스 이용이 가능합니다.");
            response.sendRedirect("indexInfo.do");
        }

        String to = userDAO.getUserEmail(userId);
        //String host = "http://localhost:8080/";
        String host = "http://cloud.swdev.kr:4006/";
        String subject = userId + "님, LeafPage 계정 인증 메일입니다.";
        String content = generateEmailContent(host, to, userId, inactiveUserId);
        boolean isEmailSent = sendEmail("TESTsjh8924@gmail.com", to, subject, content);

        if (isEmailSent) {
            return "sendEmailView.do"; // 화면 이동
        } else {
            session.setAttribute("msg", "[Error] 오류가 발생했습니다.");
            response.sendRedirect("emailResendView.do");
        }

        return "none";
    }

    private String generateEmailContent(String host, String to, String userId, String inactiveUserId) {
        String sendCodeToURL = "checkEmail.do?code=" + new SHA256().getSHA256(to);
        String sendUserID =  "&userId=" + userId;
        String sendInactiveUserId = "";
        if(inactiveUserId != null) {
            sendInactiveUserId = "&inactiveUserId=" + inactiveUserId;
        }
        return "다음 링크에 접속하시면 이메일 인증이 완료됩니다." +
                "<br><a href='" + host + sendCodeToURL + sendUserID + sendInactiveUserId + "'><strong style='font-size: 20px;'>이메일 인증하기</strong></a>";
    }

    private boolean sendEmail(String from, String to, String subject, String content) {
        try {
            Properties properties = getMailProperties();
            Authenticator auth = new com.leafpage.util.Gmail();
            Session ses = Session.getInstance(properties, auth);
            ses.setDebug(true);
            MimeMessage msg = new MimeMessage(ses);
            msg.setSubject(subject);
            Address fromAddr = new InternetAddress(from);
            msg.setFrom(fromAddr);
            Address toAddr = new InternetAddress(to);
            msg.addRecipient(Message.RecipientType.TO, toAddr);
            msg.setContent(content, "text/html; charset=UTF8");
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.user", "TESTsjh8924@gmail.com");
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        return properties;
    }
}
