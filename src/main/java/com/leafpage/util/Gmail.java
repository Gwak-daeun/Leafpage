package com.leafpage.util;

/*
pom.xml에 외부라이브러리 넣기 (dependency)
- javamail 1.4.7
- javabeans activation framework(JAF) 1.1.1
 */
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("TESTsjh8924@gmail.com", "wsmtrgplypwczztf");
    }
}