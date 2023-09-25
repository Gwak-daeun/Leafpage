package com.leafpage.dao;

import com.leafpage.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeeLikeyDAO {

    private Connection conn = null;
    PreparedStatement pstmp = null;
    private ResultSet rs = null;

    //좋아요 누른건지 조회 select
    //좋아요 취소 delete
    //좋아요 하는 insert
    public int checkLike(Long userNo, String isbn) {

        String SQL = "SELECT * FROM likey WHERE user_no = ? AND ISBN = ?";

        if (userNo == null) {
            return 0;
        }

        try {
            conn = DBUtil.getConnection();
            pstmp = conn.prepareStatement(SQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);
            rs = pstmp.executeQuery();
            System.out.println(rs);

            if(rs.next()) {
                return 1;           //값이 있으면 1 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(rs, pstmp, conn);
        }
        return 0;
    }

    public int deleteLike(Long userNo, String isbn) {

        String deleteSQL = "DELETE FROM likey WHERE user_no = ? AND ISBN = ?";

        try {
            conn = DBUtil.getConnection();
            pstmp = conn.prepareStatement(deleteSQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);
            pstmp.executeUpdate();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmp, conn);
        }
        return 0;
    }

    //하트 클릭할 때 데이터 추가
    public int insertLike(Long userNo, String isbn) {

        String insertSQL = "INSERT INTO likey VALUES (?, ?)";

        try {
            conn = DBUtil.getConnection();
            pstmp = conn.prepareStatement(insertSQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);
            pstmp.executeUpdate();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmp, conn);
        }
        return 0;
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
        return -1;
    }
}
