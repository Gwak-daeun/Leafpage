package com.leafpage.dao;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.leafpage.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeyDAO {

    private Connection conn = null;
    PreparedStatement pstmp = null;
    PreparedStatement dlstmt = null;
    PreparedStatement instmt = null;
    ResultSet rs = null;


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
            String SQL = "SELECT * FROM likey WHERE userNo = ? AND ISBN = ?";
            conn = DBUtil.getConnection();

            pstmp = conn.prepareStatement(SQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);

            rs = pstmp.executeQuery();


            //하트 눌려있는 경우(데이터 있는 경우), 좋아요 취소
            if (rs.next()) {
                String deleteSQL = "DELETE FROM likey WHERE userNo = ? AND ISBN = ?";

                dlstmt = conn.prepareStatement(deleteSQL);
                dlstmt.setLong(1,userNo);
                dlstmt.setString(2, isbn);
                dlstmt.executeUpdate();

                return false;

            } else {
                //하트 비어있는 경우, 좋아요 추가
                String insertSQL = "INSERT INTO likey VALUES (13, '040501813-4')";

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
        int heartCount = 0;

        String SQL = "SELECT COUNT(*) AS LIKECOUNT FROM likey WHERE ISBN = ?";


        try {
            conn = DBUtil.getConnection();
            pstmp = conn.prepareStatement(SQL);
            pstmp.setString(1, isbn);
            rs = pstmp.executeQuery();

            if(rs.next()) {
                heartCount = rs.getInt("LIKECOUNT");
                return heartCount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmp, conn);
        }
        return -1;  //DB 문제?
    }
}
