package com.leafpage.dao;

import com.leafpage.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeeLikeyDAO {

    private Connection conn = null;
    PreparedStatement pstmp = null;
    private ResultSet rs = null;
    PreparedStatement dlstmt = null;
    PreparedStatement instmt = null;

    public class likeStatus {
        private boolean likeStatus;
        private int likeCount;
    }

    //하트 클릭할 때 데이터 추가
    public boolean like(Long userNo, String isbn) {

        try {
            String SQL = "SELECT * FROM likey WHERE user_no = ? AND ISBN = ?";
            conn = DBUtil.getConnection();
            pstmp = conn.prepareStatement(SQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);
            ResultSet rs = null;
            rs = pstmp.executeQuery();

            boolean likeStatus = rs.next();


            //하트 눌려있는 경우(데이터 있는 경우), 좋아요 취소
            if (likeStatus) {
                String deleteSQL = "DELETE FROM likey WHERE user_no = ? AND ISBN = ?";

                dlstmt = conn.prepareStatement(deleteSQL);
                dlstmt.setLong(1,userNo);
                dlstmt.setString(2, isbn);
                dlstmt.executeUpdate();

                return false;

            } else {
                //하트 비어있는 경우, 좋아요 추가
                String insertSQL = "INSERT INTO likey VALUES (?, ?)";    //040501813-4

                instmt = conn.prepareStatement(insertSQL);
                instmt.setLong(1, userNo);
                instmt.setString(2, isbn);
                instmt.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmp, conn);
            DBUtil.close(null, dlstmt, null);
            DBUtil.close(null, instmt, null);
        }
        return false;
    }

    //하트 수 세기(조회)
    public int likeCount(String isbn) {
        int likeCount = 0;

        try {
            String SQL = "SELECT COUNT(*) AS LIKECOUNT FROM likey WHERE ISBN = ?";

            conn = DBUtil.getConnection();
            pstmp = conn.prepareStatement(SQL);
            pstmp.setString(1, isbn);
            rs = pstmp.executeQuery();

            if(rs.next()) {
                likeCount = rs.getInt("LIKECOUNT");
                return likeCount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmp, conn);
        }
        return -1;
    }
}
