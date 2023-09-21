package com.leafpage.domain.user;

import com.leafpage.dao.UserDAO;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
public class Email {
    private static final int MIN_EMAIL_LENGTH = 6;
    private static final int MAX_EMAIL_LENGTH = 32;

    private final String userEmail;

    public Email(String userEmail) {
        validate(userEmail);
        this.userEmail = userEmail;
    }

    private void validate(String userEmail) {
        if (userEmail == null || isBlank(userEmail)) {
            throw new IllegalArgumentException("이메일을 입력하지 않았습니다.");
        }
        if (isInValidEmailMINLength(userEmail)) {
            throw new IllegalArgumentException("이메일은 "+MIN_EMAIL_LENGTH+"자 이상입니다.");
        }
        if (isInValidEmailMAXLength(userEmail)) {
            throw new IllegalArgumentException("이메일이 너무 깁니다. 다른 이메일을 입력해주세요.");
        }
        if (isInValidEmail(userEmail)) {
            throw new IllegalArgumentException("이메일이 형식에 맞지 않습니다. 다른 이메일을 입력해주세요.");
        }
    }

    private boolean isInValidEmailMINLength(String userEmail) {
        return userEmail.length() < MIN_EMAIL_LENGTH;
    }

    private boolean isInValidEmailMAXLength(String userEmail) {
        return userEmail.length() > MAX_EMAIL_LENGTH;
    }

    private boolean isBlank(String userEmail) {
        return userEmail.trim().isEmpty();
    }

    private boolean isInValidEmail(String userEmail) {
        String regex = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userEmail);
        return !matcher.matches();
    }

}
