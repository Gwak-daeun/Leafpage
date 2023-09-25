package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.UserDAO;

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
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            userId = (String) session.getAttribute("inactiveIdForActive");
            System.out.println("new:" + userId);
        }

        boolean emailChecked = userDAO.getUserEmailChecked(userId);

        if (emailChecked) {
            session.setAttribute("msg", "이미 이메일 인증이 완료되었습니다. 정상적인 서비스 이용이 가능합니다.");
            response.sendRedirect("indexInfo.do");
        }

        //구글 smtp가 기본적으로 제공하는 양식을 그대로 사용
        String host = "http://cloud.swdev.kr:4006/";
        String from = "TESTsjh8924@gmail.com";  //보내는 사람
        String to = userDAO.getUserEmail(userId);  //받는 사람
        System.out.println("to:" + to);
        String subject = "LeafPage 이메일 인증";
        String content = "다음 링크에 접속하시면 이메일 인증이 완료됩니다." +
                "<a href='" + host + "checkEmail.do?code=" + new com.leafpage.util.SHA256().getSHA256(to) + "'>이메일 인증하기</a>";

        //구글 smtp서버를 이용하기 위해서 정보 설정하기
        //SMTP 서버 이용하기(22.05.30~) : 구글 2단계인증 > 앱비밀번호 생성(Gmail SMTP) > 앱 비밀번호 16자리를 Gmail 비밀번호로 사용
        Properties p = new Properties();
        p.put("mail.smtp.user", from);  //나의 구글 이메일 계정
        p.put("mail.smtp.host", "smtp.googlemail.com");  //구글에서 제공하는 smtp 서버
        p.put("mail.smtp.port", "465");  //465번 포트 사용 (정해져있음 - 구글서비스가 제공)
        p.put("mail.smtp.starttls.enable", "true");  //starttls의 사용 가능 => true로 설정
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.debug", "true");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback", "false");

        System.out.println("여기111!");
        //인증메일 발송하기
        try {
            System.out.println("여기222222!");
            Authenticator auth = new com.leafpage.util.Gmail();
            Session ses = Session.getInstance(p, auth);  //구글 계정으로 Gmail 인증 수행
            ses.setDebug(true);  //디버깅 설정
            MimeMessage msg = new MimeMessage(ses);  //MimeMesssage 객체로 실제로 메일을 보낼 수 있게 함
            msg.setSubject(subject);  //메일 제목
            Address fromAddr = new InternetAddress(from);
            msg.setFrom(fromAddr);  //보내는 사람 정보 넣기
            Address toAddr = new InternetAddress(to);
            msg.addRecipient(Message.RecipientType.TO, toAddr);  //받는 사람 정보 넣기
            msg.setContent(content, "text/html; charset=UTF8");  //메일 내용 (UTF8 인코딩으로 전송)
            Transport.send(msg);  //메일 전송
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "[Error] 오류가 발생했습니다.");
            return "emailResendView.do";
        }
        return "sendEmailView.do"; //화면이동
    }
}
