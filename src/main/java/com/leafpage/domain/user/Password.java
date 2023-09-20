package com.leafpage.domain.user;

import com.leafpage.util.SHA256;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
public class Password {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 32;

    private final String userPassword;

    public Password(String userPassword) {
        validate(userPassword);
        this.userPassword = SHA256.getSHA256(userPassword);
    }

    private void validate(String userPassword) {
        if (userPassword == null || isBlank(userPassword)) {
            throw new IllegalArgumentException("비밀번호를 입력하지 않았습니다.");
        }
        if (isInValidPasswordMINLength(userPassword)) {
            throw new IllegalArgumentException("비밀번호는 8자 이상입니다.");
        }
        if (isInValidPasswordMAXLength(userPassword)) {
            throw new IllegalArgumentException("비밀번호가 너무 깁니다. 다른 비밀번호를 입력해주세요.");
        }
        if (isInValidPassword(userPassword)) {
            throw new IllegalArgumentException("비밀번호는 영문자+숫자+특수문자를 포함해야 합니다.");
        }
    }

    private boolean isInValidPasswordMINLength(String userPassword) {
        return userPassword.length() < MIN_PASSWORD_LENGTH;
    }

    private boolean isInValidPasswordMAXLength(String userPassword) {
        return userPassword.length() > MAX_PASSWORD_LENGTH;
    }

    private boolean isBlank(String userPassword) {
        return userPassword.trim().isEmpty();
    }

    private boolean isInValidPassword(String userPassword) {
        String regex = "^(?=.*?[a-z,A-Z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userPassword);
        return !matcher.matches();
    }
}