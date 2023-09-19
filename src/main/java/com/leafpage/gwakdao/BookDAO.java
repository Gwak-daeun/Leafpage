package com.leafpage.gwakdao;

import com.leafpage.gwakdto.BookContentDTO;
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

        String SQL = "select r.rental_no as all_rentals, b.book_name, b.book_author_name, r.scheduled_return_date, r.rental_date, r.rental_no, r.scroll_y, r.modal_width \n" +
                "from users u \n" +
                "join book_rental r\n" +
                "on u.user_no = r.user_no\n" +
                "join books b\n" +
                "on r.ISBN = b.ISBN \n" +
                "where u.user_no = 2\n" +
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
                "where book_rental.user_no = 2;";
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

        String SQL = "select b.book_name, b.book_author_name, r.actual_return_date\n" +
                "from users u \n" +
                "join book_rental r\n" +
                "on u.user_no = r.user_no\n" +
                "join books b\n" +
                "on r.ISBN = b.ISBN\n" +
                "where u.user_no = 2\n" +
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

    public List<BookContentDTO> getLendingBookContent() throws IOException {

        //txt파일 가져올 때
//        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\user\\Desktop\\book.txt"));
//
//        List<String> bookText = new ArrayList<>();
//
//        for (String line : lines) {
//            String word = line.replace("\\n", "<br>");
//            bookText.add(word);
//        }


        String SQL = "select books.book_content\n" +
                "from books\n" +
                "join book_rental\n" +
                "on books.ISBN = book_rental.ISBN\n" +
                "join users\n" +
                "on users.user_no = book_rental.user_no\n" +
                "where users.user_no = 2;";
        //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookContentDTO> bookText = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookContentDTO books = new BookContentDTO(
                    rs.getString(1)
                );
                bookText.add(books);
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

        return bookText;

    }

    public int saveBookScrollY(int modalY, int modalWidth, int rental_no) {

        String SQL = "";

        System.out.println("MODAL WIDTH: " + modalWidth);

        SQL = "update book_rental set scroll_y = ?, modal_width = ? where rental_no = 2;";

        System.out.println("SQL: " + SQL);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, modalY);
            pstmt.setInt(2, modalWidth);
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