package com.leafpage.dao;

import com.leafpage.domain.user.Id;
import com.leafpage.domain.user.Password;
import com.leafpage.dto.UserDTO;
import com.leafpage.util.DBUtil;
import com.leafpage.util.SHA256;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    String loginSQL = "SELECT user_password, user_role, user_state FROM users WHERE user_id = ?";
    String signupSQL = "INSERT INTO users VALUES (NULL,?,?,?,?,?,?,?,?,?,false,NOW())";
    String findUserByIdSQL = "SELECT user_id FROM users WHERE user_id = ?";
    String findIdByEmailOrTelSQL = "SELECT user_id FROM users WHERE user_email = ? OR user_tel = ?";
    String findPwByEmailOrTelSQL = "SELECT user_security_question, user_security_answer FROM users WHERE (user_email = ? OR user_tel =?) AND (user_id = ?)";
    String findUserByEmailSQL = "SELECT user_email FROM users WHERE user_email = ?";
    String findUserByTelSQL = "SELECT user_tel FROM users WHERE user_tel = ?";
    String getUserEmailSQL = "SELECT user_email FROM users WHERE user_id = ?";
    String getUserTelSQL = "SELECT user_tel FROM users WHERE user_id = ?";
    String getUserEmailCheckedSQL = "SELECT user_email_checked FROM users WHERE user_id = ?";
    String getUserNoSQL = "SELECT user_no FROM users WHERE user_id = ?";
    String setUserEmailCheckedSQL = "UPDATE users SET user_email_checked = true WHERE user_id = ?";
    String changeNewPasswordSQL = "UPDATE users SET user_password = ? WHERE user_id = ?";
    String setUserStateWithdrawalSQL = "UPDATE users SET user_state = ?, user_tel = null, user_email = null, user_email_checked = false WHERE user_id = ? AND user_password = ?";
    String setUserStateInactiveSQL = "UPDATE users SET user_state = ?, user_email_checked = false WHERE user_id = ?";
    String updateUserInfoSQL = "UPDATE users SET user_tel = ?, user_email=?, user_email_checked=false WHERE user_id = ? AND user_password = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public int login(String userId, String userPassword) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(loginSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();  //executeQuery : 데이터를 조회하고 ResultSet으로 결과 확인
            //사용자가 입력한 ID에 해당하는 PW(DB)가 사용자가 입력한 PW(input)와 같은지를 비교
            if (rs.next()) {
                if (rs.getString(1).equals(SHA256.getSHA256(userPassword))) {
                    if (rs.getString(2).equals("회원")) {
                        if (rs.getString(3).equals("일반회원")) {return 1;}  //회원 로그인 성공
                        else if (rs.getString(3).equals("휴면회원")) {return 2;}  //휴면회원 로그인시도
                        else if (rs.getString(3).equals("블랙회원")) {return 3;}  //블랙회원 로그인시도
                        else if (rs.getString(3).equals("탈퇴회원")) {return 4;}  //탈퇴회원 로그인시도
                    } else if (rs.getString(2).equals("관리자")) {return 0;}  //관리자 로그인 성공
                } else {return -1;}  //비밀번호 틀림
            }
            return -2;  //아이디 없음
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -3;  // 데이터베이스 오류
    }


    //회원가입
    public int signup(UserDTO user) {
        //no,id,pw,email,tel,state,role,securityq,securityan
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(signupSQL);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserEmail());
            pstmt.setString(4, user.getUserTel());
            pstmt.setString(5, user.getUserState());
            pstmt.setString(6, user.getUserRole());
            pstmt.setString(7, user.getUserSecurityQuestion());
            pstmt.setString(8, user.getUserSecurityAnswer());
            pstmt.setString(9, user.getUserEmailHash());
            return pstmt.executeUpdate();  //executeUpdate : 실제로 영향을 받은 데이터의 개수 반환 => 성공한다면 1 반환됨
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  // 회원가입 실패
    }

    //아이디 중복확인
    public int findUserById(String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(findUserByIdSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return 1;  //아이디가 있다면
            } else {
                return 0; //아이디가 없다면
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  //데이터베이스 오류
    }

    //아이디찾기
    public String findIdByEmailOrTel(String inputEmail, String inputTel) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(findIdByEmailOrTelSQL);
            pstmt.setString(1, inputEmail);
            pstmt.setString(2, inputTel);
            rs = pstmt.executeQuery();
            System.out.println(rs.next());
            return rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return null;
    }

    //비밀번호찾기
    public int findPwByEmailOrTel(String inputId, String inputEmail, String inputTel, String selectQuestion, String inputAnswer) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(findPwByEmailOrTelSQL);
            pstmt.setString(1, inputEmail);
            pstmt.setString(2, inputTel);
            pstmt.setString(3, inputId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(selectQuestion) && rs.getString(2).equals(inputAnswer)) {
                    return 1;  //OK! 통과!
                } else {
                    return 0;  // 정보는 있는 사용자인데 질문,대답이 틀림
                }
            } else {
                return -1;  // 조회되지 않는 사용자
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -2;  //error
    }

    //새 비밀번호 변경
    public int changeNewPassword (String newPassword, String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(changeNewPasswordSQL);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, userId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  // 데이터베이스 오류
    }

    //이메일 중복확인
    public int findUserByEmail(String userEmail) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(findUserByEmailSQL);
            pstmt.setString(1, userEmail);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return 1;  //이메일이 있다면
            } else {
                return 0; //이메일이 없다면
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  //데이터베이스 오류
    }

    //전화번호 중복확인
    public int findUserByTel(String userTel) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(findUserByTelSQL);
            pstmt.setString(1, userTel);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return 1;  //전화번호가 있다면
            } else {
                return 0; //전화번호가 없다면
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  //데이터베이스 오류
    }

    // 사용자의 ID 값을 받아서 userTel을 반환
    public String getUserTel(String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(getUserTelSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return null;  // 데이터베이스 오류
    }

    // 사용자의 ID 값을 받아서 userEmail을 반환
    public String getUserEmail(String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(getUserEmailSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return null;  // 데이터베이스 오류
    }

    //사용자가 현재 Email 인증이 되었는지 확인
    public boolean getUserEmailChecked (String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(getUserEmailCheckedSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return false;  // 데이터베이스 오류
    }

    //회원번호 가져오기
    public int getUserNo (String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(getUserNoSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  // 데이터베이스 오류
    }

    //특정한 사용자의 Email 인증을 수행
    public boolean setUserEmailChecked (String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(setUserEmailCheckedSQL);
            pstmt.setString(1, userId);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return false;  // 데이터베이스 오류
    }

    //탈퇴하기
    public int setUserStateWithdrawal(String userId, String userPassword, String userState) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(setUserStateWithdrawalSQL);
            pstmt.setString(1, userState);
            pstmt.setString(2, userId);
            pstmt.setString(3, userPassword);
            return pstmt.executeUpdate();  //성공하면 1, 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  // 데이터베이스 오류
    }

    //휴면기능
    public int setUserStateInactive(String userId, String userState) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(setUserStateInactiveSQL);
            pstmt.setString(1, userState);
            pstmt.setString(2, userId);
            return pstmt.executeUpdate();  //성공하면 1, 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  // 데이터베이스 오류
    }

    //내 정보 수정
    public int updateUserInfo(String userId, String userTel, String userEmail, String userPassword) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(updateUserInfoSQL);
            pstmt.setString(1, userTel);
            pstmt.setString(2, userEmail);
            pstmt.setString(3, userId);
            pstmt.setString(4, userPassword);
            return pstmt.executeUpdate();  //성공하면 1, 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  // 데이터베이스 오류
    }
}