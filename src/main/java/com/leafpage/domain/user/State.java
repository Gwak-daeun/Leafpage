package com.leafpage.domain.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class State {

    List<String> validStates = new ArrayList<>(Arrays.asList(
            "회원",
            "관리자"
    ));

    private final String userState;

    public State(String userState) {
        validate(userState);
        this.userState = userState;
    }

    private void validate(String userState) {
        if (isInvalidState(userState)) {
            throw new IllegalArgumentException("유효하지 않은 회원상태입니다.");
        }
    }

    private boolean isInvalidState(String userState) {
        return !validStates.contains(userState);
    }
}