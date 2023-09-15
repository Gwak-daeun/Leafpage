package com.leafpage.dao;

import com.leafpage.dto.BookDTO;
import com.leafpage.dto.MypageBooksDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private Connection conn = null;
    DataSource dataSource = null;

    public BookDAO() {
        try {
            Context init = new InitialContext();
            dataSource = (DataSource)init.lookup("java:comp/env/jdbc/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MypageBooksDTO> getUserBookInfo() throws IOException {

        ArrayList<MypageBooksDTO> userBookList = null;

        String SQL = "select r.rental_no as all_rentals, b.book_name, a.author_name, r.scheduled_return_date, r.rental_date\n" +
                "from users u \n" +
                "join book_rental r\n" +
                "on u.user_no = r.user_no\n" +
                "join books b\n" +
                "on r.ISBN = b.ISBN\n" +
                "join authors a\n" +
                "on b.author_no = a.author_no\n" +
                "where r.actual_return_date is null;";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        userBookList = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MypageBooksDTO books = new MypageBooksDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                );
                userBookList.add(books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {conn.close();}
                if (pstmt != null) {pstmt.close();}
                if (rs != null) {rs.close();}
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return userBookList;
    }

}
