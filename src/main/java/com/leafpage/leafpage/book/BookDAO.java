package com.leafpage.leafpage.book;

import com.leafpage.leafpage.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAO {

    public ArrayList<BookDTO> getBook(int bookID, int pageNumber) {
        ArrayList<BookDTO> bookList = null;
        String SQL = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            SQL = "select * from book";
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            bookList = new ArrayList<BookDTO>();
            while (rs.next()) {
                BookDTO book = new BookDTO(
                        rs.getInt(1),
                        rs.getString(2)
                );
                bookList.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bookList;
        }
    }
}
