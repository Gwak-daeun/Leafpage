package com.leafpage.domain.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class SecurityQuestion {

    private final String userSecurityQuestion;
    private final List<String> validSecurityQuestions = new ArrayList<>(Arrays.asList(
            "나의 보물 1호는?",
            "출생지는 어디인가요?",
            "첫 번째 자동차 모델은 무엇인가요?",
            "초등학교 이름은 무엇인가요?"
    ));

    public SecurityQuestion(String userSecurityQuestion) {
        validate(userSecurityQuestion);
        this.userSecurityQuestion = userSecurityQuestion;
    }

    private void validate(String userSecurityQuestion) {
        if (isInvalidSecurityQuestion(userSecurityQuestion)) {
            throw new IllegalArgumentException("유효하지 않은 인증 질문입니다.");
        }
    }

    private boolean isInvalidSecurityQuestion(String userSecurityQuestion) {
        return !validSecurityQuestions.contains(userSecurityQuestion);
    }
}
