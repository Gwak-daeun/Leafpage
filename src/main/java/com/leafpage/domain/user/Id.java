package com.leafpage.domain.user;

import com.leafpage.dao.UserDAO;
import lombok.Getter;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Id {
    private static final int MIN_ID_LENGTH = 6;
    private static final int MAX_ID_LENGTH = 32;

    private final String userId;

    public Id(String userId) {
        validate(userId);
        this.userId = userId;
    }

    private void validate(String userId) {
        if (userId == null || isBlank(userId)) {
            throw new IllegalArgumentException("아이디를 입력하지 않았습니다.");
        }
        if (isInValidId(userId)) {
            throw new IllegalArgumentException("아이디는 영어와 숫자만 가능합니다.");
        }
        if (isInValidIdMINLength(userId)) {
            throw new IllegalArgumentException("아이디는 6자 이상입니다.");
        }
        if (isInValidIdMAXLength(userId)) {
            throw new IllegalArgumentException("아이디가 너무 깁니다. 다른 아이디를 입력해주세요.");
        }
        if(isDuplicateId(userId)) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다. 다른 아이디를 입력해주세요.");
        }
    }

    private boolean isBlank(String userId) {
        return userId.trim().isEmpty();
    }

    private boolean isInValidIdMINLength(String userId) {
        return userId.length() < MIN_ID_LENGTH;
    }

    private boolean isInValidIdMAXLength(String userId) {
        return userId.length() > MAX_ID_LENGTH;
    }

    private boolean isInValidId(String userId) {
        String regex = "^(?=.*?[a-z,A-Z])(?=.*?[0-9]).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userId);
        return !matcher.matches();
    }

    private boolean isDuplicateId(String userId) {
        return new UserDAO().findUserById(userId) == 1;
    }
}