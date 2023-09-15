package com.leafpage.dao;

import com.leafpage.dto.UserDTO;
import com.leafpage.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    String loginSQL = "SELECT user_password, user_role FROM users WHERE user_id = ?";
    String signupSQL = "INSERT INTO users VALUES (NULL,?,?,?,?,?,?,?,?)";
    Connection conn = DBUtil.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public int login(String userId, String userPassword) {
        try {
            pstmt = conn.prepareStatement(loginSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();  //executeQuery : 데이터를 조회하고 ResultSet으로 결과 확인
            //사용자가 입력한 ID에 해당하는 PW(DB)가 사용자가 입력한 PW(input)와 같은지를 비교
            if (rs.next()) {
                if (rs.getString(1).equals(userPassword)) {
                    if (rs.getString(2).equals("회원")) {
                        return 1;  //회원 로그인 성공
                    } else if (rs.getString(2).equals("관리자")) {
                        return 2;  //관리자 로그인 성공
                    }
                } else {
                    return 0;  //비밀번호 틀림
                }
            }
            return -1;  //아이디 없음
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -2;  // 데이터베이스 오류
    }

    public int signup(UserDTO user) {
        //no,id,pw,email,tel,state,role,securityq,securityan
        try {
            pstmt = conn.prepareStatement(signupSQL);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserEmail());
            pstmt.setString(4, user.getUserTel());
            pstmt.setString(5, user.getUserState());
            pstmt.setString(6, user.getUserRole());
            pstmt.setString(7, user.getUserSecurityQuestion());
            pstmt.setString(8, user.getUserSecurityAnswer());

            return pstmt.executeUpdate();  //executeUpdate : 실제로 영향을 받은 데이터의 개수 반환 => 성공한다면 1 반환됨
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;  // 회원가입 실패
    }
}