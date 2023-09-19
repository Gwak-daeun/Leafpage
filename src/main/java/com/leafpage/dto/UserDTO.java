package com.leafpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userNo;
    private String userId;
    private String userPassword;
    private String userEmail;
    private String userTel;
    private String userState;
    private String userRole;
    private String userSecurityQuestion;
    private String userSecurityAnswer;
    private String userEmailHash;
    private boolean userEmailChecked;
}
