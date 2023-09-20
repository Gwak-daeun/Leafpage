package com.leafpage.domain.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Role {

    List<String> validRoles = new ArrayList<>(Arrays.asList(
            "일반회원",
            "탈퇴회원",
            "블랙회원",
            "휴면회원"
    ));

    private final String userRole;

    public Role(String userRole) {
        validate(userRole);
        this.userRole = userRole;
    }

    private void validate(String userRole) {
        if (isInvalidRole(userRole)) {
            throw new IllegalArgumentException("유효하지 않은 회원권한입니다..");
        }
    }

    private boolean isInvalidRole(String userRole) {
        return !validRoles.contains(userRole);
    }
}
