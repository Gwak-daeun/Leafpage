package com.leafpage.gwakdao;

import com.leafpage.gwakdto.MypageBooksDTO;
import com.leafpage.gwakdto.MypageReturnedBooksDTO;
import com.leafpage.gwakdto.MypageTotalRentalDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
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

    public ArrayList<MypageBooksDTO> getUserLendingBook() throws IOException {

        ArrayList<MypageBooksDTO> userBookList = null;

        String SQL = "select r.rental_no as all_rentals, b.book_name, a.author_name, r.scheduled_return_date, r.rental_date, u.user_no, r.pcY, r.modalWidth \n" +
                "from users u \n" +
                "join book_rental r\n" +
                "on u.user_no = r.user_no\n" +
                "join books b\n" +
                "on r.ISBN = b.ISBN \n" +
                "join authors a\n" +
                "on b.author_no = a.author_no\n" +
                "where u.user_no = 5\n" +
                "and r.actual_return_date is null;";
        //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함

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
                    rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)
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

    public int getTotalRentals() {
        int userTotalRentals = 0;

        String SQL = "select count(*) as rental_num\n" +
                "from book_rental\n" +
                "where book_rental.user_no = 5;";
        //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
//        userTotalRentals = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MypageTotalRentalDTO totalRentals = new MypageTotalRentalDTO(
                        rs.getInt(1)
                );
                userTotalRentals = totalRentals.getRental_num();
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

        return userTotalRentals;
    }

    public ArrayList<MypageReturnedBooksDTO> getUserReturnedBook() {
        ArrayList<MypageReturnedBooksDTO> userReturnedBookList = null;

        String SQL = "select  b.book_name, a.author_name, r.actual_return_date\n" +
                "from users u \n" +
                "join book_rental r\n" +
                "on u.user_no = r.user_no\n" +
                "join books b\n" +
                "on r.ISBN = b.ISBN \n" +
                "join authors a\n" +
                "on b.author_no = a.author_no\n" +
                "where u.user_no = 4\n" +
                "and r.actual_return_date is not null;";
        //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        userReturnedBookList = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MypageReturnedBooksDTO books = new MypageReturnedBooksDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                userReturnedBookList.add(books);
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

        return userReturnedBookList;
    }

    public List<String> getLendingBookContent() throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\user\\Desktop\\book.txt"));

        List<String> bookText = new ArrayList<>();

        for (String line : lines) {
            String word = line.replace("\\n", "<br>");
            bookText.add(word);
        }

        return bookText;

    }

    public int saveBookScrollY(int modalY, int modalWidth, int rental_no) {

        String SQL = "";

        System.out.println("MODAL WIDTH: " + modalWidth);

//        if (modalWidth == 321) {
            SQL = "update book_rental set mobileY = ? where rental_no = 5;";
//        }
//        if (modalWidth > 321) {
//            SQL = "update book_rental set pcY = ? where rental_no = 5;";
//        }

        System.out.println("SQL: " + SQL);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, modalY);
            System.out.println("PSTMT : " + pstmt);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) { conn.close();}
                if (pstmt != null) { pstmt.close();}
                if (rs != null) {rs.close();}
            } catch (Exception e){e.printStackTrace();}
        }
        return -1;
    }

}
