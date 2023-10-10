package com.leafpage.dao;

import com.leafpage.dto.UserDTO;
import com.leafpage.util.DBUtil;
import com.leafpage.util.SHA256;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO instance;
    String loginSQL = "SELECT user_password, user_role, user_state FROM users WHERE user_id = ?";
    String signupSQL = "INSERT INTO users VALUES (NULL,?,?,?,?,NOW(),?,?,?,?,?,false)";
    String findUserByIdSQL = "SELECT user_id FROM users WHERE user_id = ?";
    String findIdByEmailOrTelSQL = "SELECT user_id FROM users WHERE user_email = ? OR user_tel = ?";
    String findPwByEmailOrTelSQL = "SELECT user_security_question, user_security_answer FROM users WHERE (user_email = ? OR user_tel =?) AND (user_id = ?)";
    String findUserByEmailSQL = "SELECT user_email FROM users WHERE user_email = ?";
    String findUserByTelSQL = "SELECT user_tel FROM users WHERE user_tel = ?";
    String getUserEmailSQL = "SELECT user_email FROM users WHERE user_id = ?";
    String getUserEmailHashSQL = "SELECT user_email_hash FROM users WHERE user_id = ?";
    String getUserTelSQL = "SELECT user_tel FROM users WHERE user_id = ?";
    String getUserEmailCheckedSQL = "SELECT user_email_checked FROM users WHERE user_id = ?";
    String getUserNoSQL = "SELECT user_no FROM users WHERE user_id = ?";
    String setUserEmailCheckedSQL = "UPDATE users SET user_email_checked = true WHERE user_id = ?";
    String changeNewPasswordSQL = "UPDATE users SET user_password = ? WHERE user_id = ?";
    String setUserStateWithdrawalSQL = "UPDATE users SET user_state = ?, user_tel = 'withdrawal', user_email = 'withdrawal', user_email_checked = false WHERE user_id = ? AND user_password = ?";
    String setUserStateInactiveSQL = "UPDATE users SET user_state = ?, user_email_checked = false WHERE user_id = ?";
    String updateUserInfoSQL = "UPDATE users SET user_tel = ?, user_email=?, user_email_checked=false WHERE user_id = ? AND user_password = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private UserDAO() {}

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

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
                        if (rs.getString(3).equals("일반회원")) {
                            return 1;
                        }  //회원 로그인 성공
                        else if (rs.getString(3).equals("휴면회원")) {
                            return 2;
                        }  //휴면회원 로그인시도
                        else if (rs.getString(3).equals("블랙회원")) {
                            return 3;
                        }  //블랙회원 로그인시도
                        else if (rs.getString(3).equals("탈퇴회원")) {
                            return 4;
                        }  //탈퇴회원 로그인시도
                    } else if (rs.getString(2).equals("관리자")) {
                        return 0;
                    }  //관리자 로그인 성공
                } else {
                    return -1;
                }  //비밀번호 틀림
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
    public int changeNewPassword(String newPassword, String userId) {
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

    // 사용자의 ID 값을 받아서 userEmailHash을 반환
    public String getUserEmailHash(String userId) {
        try {
            System.out.println("getUserEmailHash 1: "+userId);
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(getUserEmailHashSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("getUserEmailHash 2: "+rs.getString(1));
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
    public boolean getUserEmailChecked(String userId) {
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
    public Long getUserNo(String userId) {
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(getUserNoSQL);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1L;  // 데이터베이스 오류
    }

    //특정한 사용자의 Email 인증을 수행
    public boolean setUserEmailChecked(String userId) {
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

    //상태변경
    public int setUserState(String userId, String userState) {
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

    public List<UserDTO> userList(int pageNum, int amount) {
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = " select U.user_id, U.user_joining_date, U.user_email, U.user_tel, U.user_state " +
                    "from (SELECT ROW_NUMBER() OVER (ORDER BY user_joining_date) " +
                    "AS rn, u.user_id, u.user_joining_date, u.user_email, u.user_tel, u.user_state " +
                    "from users u) U " +
                    "WHERE rn > ? AND rn <= ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ((pageNum - 1) * amount));
            pstmt.setInt(2, (pageNum * amount));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDTO UserDTO = new UserDTO();
                UserDTO.setUserId(rs.getString("user_id"));
                UserDTO.setUserJoiningDate(rs.getString("user_joining_date"));
                UserDTO.setUserEmail(rs.getString("user_email"));
                UserDTO.setUserTel(rs.getString("user_tel"));
                UserDTO.setUserState(rs.getString("user_state"));
                userDTOList.add(UserDTO);
            }
            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userDTOList;

    }

    public List<UserDTO> userrentelList(int pageNum, int amount) {
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT U.user_id, U.user_joining_date, U.user_email, U.user_tel, U.user_state\n" +
                    "FROM (\n" +
                    "    SELECT ROW_NUMBER() OVER (ORDER BY COALESCE(rentalcount, 0) DESC) AS rn,\n" +
                    "           COALESCE(rentalcount, 0) as rentalcount,\n" +
                    "           user_id,\n" +
                    "           user_joining_date,\n" +
                    "           user_email,\n" +
                    "           user_tel,\n" +
                    "           user_state\n" +
                    "    FROM (\n" +
                    "        SELECT COALESCE(b.rental_count, 0) AS rentalcount,\n" +
                    "               u.user_id,\n" +
                    "               u.user_joining_date,\n" +
                    "               u.user_email,\n" +
                    "               u.user_tel,\n" +
                    "               u.user_state\n" +
                    "        FROM users u\n" +
                    "        LEFT JOIN (\n" +
                    "            SELECT user_no, COUNT(*) AS rental_count\n" +
                    "            FROM book_rental\n" +
                    "            GROUP BY user_no\n" +
                    "        ) b ON u.user_no = b.user_no\n" +
                    "    ) U1\n" +
                    ") U\n" +
                    "WHERE rn > ? AND rn <= ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ((pageNum - 1) * amount));
            pstmt.setInt(2, (pageNum * amount));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDTO UserDTO = new UserDTO();
                UserDTO.setUserId(rs.getString("user_id"));
                UserDTO.setUserJoiningDate(rs.getString("user_joining_date"));
                UserDTO.setUserEmail(rs.getString("user_email"));
                UserDTO.setUserTel(rs.getString("user_tel"));
                UserDTO.setUserState(rs.getString("user_state"));
                userDTOList.add(UserDTO);
            }
            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userDTOList;

    }

    public int getTotal() {
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "select count(*) as total from users";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("total");
            }

            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public int stateUpDate(UserDTO dto) {
        int result = 0;
        String state = null;


        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE users SET user_state = ? WHERE user_id = ?  ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserState());
            pstmt.setString(2, dto.getUserId());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return result;
    }

    public int deletesearchUser(UserDTO dto) {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "select user_no from users where user_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setUserNo(rs.getLong("user_no"));
            }


            deleteLikeyUser(conn, dto);
            deleteReviewsUser(conn, dto);
            deleteRentalUser(conn, dto);
            deleteUser(conn, dto);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return result;
    }


    public void deleteLikeyUser(Connection conn, UserDTO dto) {
        int result = 0;
        try {
            String sql = "DELETE FROM likey WHERE  user_no = ? ";

            pstmt = this.conn.prepareStatement(sql);
            pstmt.setLong(1, dto.getUserNo());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteReviewsUser(Connection conn, UserDTO dto) {
        try {
            this.conn = DBUtil.getConnection();
            String sql = "DELETE FROM reviews WHERE  user_no = ?";

            pstmt = this.conn.prepareStatement(sql);
            pstmt.setLong(1, dto.getUserNo());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteRentalUser(Connection conn, UserDTO dto) {
        int result = 0;
        try {
            this.conn = DBUtil.getConnection();
            String sql = "DELETE FROM book_rental WHERE  user_no = ? ";

            pstmt = this.conn.prepareStatement(sql);
            pstmt.setLong(1, dto.getUserNo());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteUser(Connection conn, UserDTO dto) {
        int result = 0;
        try {
            this.conn = DBUtil.getConnection();
            String sql = "DELETE FROM users WHERE  user_no = ? ";

            pstmt = this.conn.prepareStatement(sql);
            pstmt.setLong(1, dto.getUserNo());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<UserDTO> userSearchList(int pageNum, int amount, String keyword) {
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "  SELECT B.user_id, B.user_joining_date, B.user_email, B.user_tel, B.user_state" +
                    " FROM (SELECT ROW_NUMBER() OVER (ORDER BY user_joining_date) AS rn, user_id, user_joining_date, user_email, user_tel, user_state" +
                    " from users " +
                    " where concat(user_id, user_email, user_tel) LIKE ? ) B" +
                    " WHERE rn > ? AND rn <= ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setInt(2, ((pageNum - 1) * amount));
            pstmt.setInt(3, (pageNum * amount));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDTO UserDTO = new UserDTO();
                UserDTO.setUserId(rs.getString("user_id"));
                UserDTO.setUserJoiningDate(rs.getString("user_joining_date"));
                UserDTO.setUserEmail(rs.getString("user_email"));
                UserDTO.setUserTel(rs.getString("user_tel"));
                UserDTO.setUserState(rs.getString("user_state"));
                userDTOList.add(UserDTO);
            }
            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userDTOList;

    }

    public int getSearchTotal(String keyword) {
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "  SELECT count(*) as total" +
                    " FROM (SELECT ROW_NUMBER() OVER (ORDER BY user_joining_date) AS rn, user_id, user_joining_date, user_email, user_tel, user_state" +
                    " from users " +
                    " where concat(user_id, user_email, user_tel) LIKE ? ) B";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");

            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("total");
            }

            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}