package com.leafpage.dao;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.leafpage.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeyDAO {

    private Connection conn = null;

    //하트 클릭할 때 데이터 추가
    public boolean like(Long userNo, String isbn) {
//        String SQL = "INSERT INTO LIKEY VALUES (?, ?)";
//
//        PreparedStatement pstmp = null;
//        ResultSet rs = null;
//
//        try {
//            conn = DBUtil.getConnection();
//            pstmp = conn.prepareStatement(SQL);
//            pstmp.setLong(1, userNo);
//            pstmp.setString(2, ISBN);
//
//            return pstmp.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//        return -1;
        try {
            String SQL = "SELECT * FROM LIEKY WHERE userNo = ? AND ISBN = ?";
            PreparedStatement pstmp = null;
            pstmp = conn.prepareStatement(SQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);
            ResultSet rs = null;
            rs = pstmp.executeQuery();


            //하트 눌려있는 경우, 좋아요 취소
            if (rs.next()) {
                String deleteSQL = "DELETE FROM LIKEY WHERE userNo = ? AND ISBN = ?";
                PreparedStatement dlstmt = null;
                dlstmt = conn.prepareStatement(deleteSQL);
                dlstmt.setLong(1,userNo);
                dlstmt.setString(2, isbn);
                dlstmt.executeUpdate();

                return false;

            } else {
                //하트 비어있는 경우, 좋아요 추가
                String insertSQL = "INSERT INTO LIKEY VALUES (?, ?)";
                PreparedStatement instmt = null;
                instmt.setLong(1, userNo);
                instmt.setString(2, isbn);
                instmt.executeUpdate();

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }

    //하트 수 세기(조회)
    public int likeCount(String isbn) {
        int heartCount = 0;

        String SQL = "SELECT COUNT(*) AS LIKECOUNT FROM LIKEY WHERE ISBN = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                heartCount = rs.getInt("heartCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return -1;
    }
}
