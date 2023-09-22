package com.leafpage.domain.user;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
public class Tel {
    private static final int MIN_TEL_LENGTH = 10;
    private static final int MAX_TEL_LENGTH = 16;

    private final String userTel;

    public Tel(String userTel) {
        validate(userTel);
        this.userTel = userTel;
    }

    private void validate(String userTel) {
        if (userTel == null || isBlank(userTel)) {
            throw new IllegalArgumentException("전화번호를 입력하지 않았습니다.");
        }
        if (isInValidTelMINLength(userTel)) {
            throw new IllegalArgumentException("전화번호는 10자 이상입니다.");
        }
        if (isInValidTelMAXLength(userTel)) {
            throw new IllegalArgumentException("전화번호가 너무 깁니다. 다른 전화번호를 입력해주세요.");
        }
        if (isInValidTel(userTel)) {
            throw new IllegalArgumentException("전화번호가 형식에 맞지 않습니다. 휴대전화번호를 입력해주세요.");
        }
    }

    private boolean isInValidTelMINLength(String userTel) {
        return userTel.length() < MIN_TEL_LENGTH;
    }

    private boolean isInValidTelMAXLength(String userTel) {
        return userTel.length() > MAX_TEL_LENGTH;
    }

    private boolean isBlank(String userTel) {
        return userTel.trim().isEmpty();
    }

    private boolean isInValidTel(String userTel) {
        String regex = "^(01[016789]{1})[0-9]{3,4}[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userTel);
        return !matcher.matches();
    }
}
