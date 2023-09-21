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

    //좋아요 누른건지 조회 select
    //좋아요 취소 delete
    //좋아요 하는 insert
    public int checkLike(Long userNo, String isbn) {

        String SQL = "SELECT * FROM likey WHERE user_no = ? AND ISBN = ?";

        try {
            conn = DBUtil.getConnection();
            pstmp = conn.prepareStatement(SQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);
            ResultSet rs = null;
            rs = pstmp.executeQuery();

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
            dlstmt = conn.prepareStatement(deleteSQL);
            dlstmt.setLong(1, userNo);
            dlstmt.setString(2, isbn);
            dlstmt.executeUpdate();

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
            instmt = conn.prepareStatement(insertSQL);
            instmt.setLong(1, userNo);
            instmt.setString(2, isbn);
            instmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmp, conn);
            DBUtil.close(null, dlstmt, null);
            DBUtil.close(null, instmt, null);
        }
        return 0;
    }


//    public boolean like(Long userNo, String isbn) {
//
//
////            if(rs.next()) {return 1;}
////            else {return 0;};
//            //rs.next() 존재하면 1, 존재하지 않으면 0
//            //하트 눌려있는 경우(데이터 있는 경우), 좋아요 취소
//            if (rs.next()) {
//
//
//
//
//                return false;
//
//            } else {
//                //하트 비어있는 경우, 좋아요 추가
//                String insertSQL = "INSERT INTO likey VALUES (?, ?)";    //040501813-4
//
//                instmt = conn.prepareStatement(insertSQL);
//                instmt.setLong(1, userNo);
//                instmt.setString(2, isbn);
//                instmt.executeUpdate();
//
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBUtil.close(rs, pstmt, conn);
//            DBUtil.close(null, dlstmt, null);
//            DBUtil.close(null, instmt, null);
//        }
//        return false;
//    }

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
