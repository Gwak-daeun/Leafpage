package com.leafpage.dto;

import com.leafpage.domain.user.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userNo;
    private Id userId;
    private Password userPassword;
    private Email userEmail;
    private Tel userTel;
    private State userState;
    private Role userRole;
    private SecurityQuestion userSecurityQuestion;
    private String userSecurityAnswer;
    private String userEmailHash;
    private boolean userEmailChecked;

    public String getUserId() {
        return userId.getUserId();
    }

    public void setUserId(String userId) {
        this.userId = new Id(userId);
    }

    public String getUserPassword() {
        return userPassword.getUserPassword();
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = new Password(userPassword);
    }

    public String getUserEmail() {
        return userEmail.getUserEmail();
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = new Email(userEmail);
    }

    public String getUserTel() {
        return userTel.getUserTel();
    }

    public void setUserTel(String userTel) {
        this.userTel = new Tel(userTel);
    }

    public String getUserSecurityQuestion() {
        return userSecurityQuestion.getUserSecurityQuestion();
    }

    public void setUserSecurityQuestion(String userSecurityQuestion) {
        this.userSecurityQuestion = new SecurityQuestion(userSecurityQuestion);
    }

    public String getUserState() {
        return userState.getUserState();
    }

    public void setUserState(String userState) {
        this.userState = new State(userState);
    }

    public String getUserRole() {
        return userRole.getUserRole();
    }

    public void setUserRole(String userRole) {
        this.userRole = new Role(userRole);
    }
}
