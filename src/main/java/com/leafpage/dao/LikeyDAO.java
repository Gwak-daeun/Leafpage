package com.leafpage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeyDAO {

    private Connection conn = null;

    public int like(Long userNo, String ISBN) {

        String SQL = "SELECT COUNT(*) AS LIKECOUNT FROM LIKEY WHERE ISBN = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return -1;
    }
}
