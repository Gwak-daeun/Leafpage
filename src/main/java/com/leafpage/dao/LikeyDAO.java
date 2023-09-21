package com.leafpage.dao;

import com.leafpage.dto.*;
import com.leafpage.util.DBUtil;
import com.leafpage.util.SHA256;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LikeyDAO {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    PreparedStatement dlstmt = null;
    PreparedStatement instmt = null;



    //하트 클릭할 때 데이터 추가
    public boolean like(Long userNo, String isbn) {

        try {
            String SQL = "SELECT * FROM likey WHERE user_no = ? AND ISBN = ?";
            conn = DBUtil.getConnection();
            PreparedStatement pstmp = null;
            pstmp = conn.prepareStatement(SQL);
            pstmp.setLong(1, userNo);
            pstmp.setString(2, isbn);
            ResultSet rs = null;
            rs = pstmp.executeQuery();

            //하트 눌려있는 경우(데이터 있는 경우), 좋아요 취소
            if (rs.next()) {
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
            DBUtil.close(rs, pstmt, conn);
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
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                heartCount = rs.getInt("LIKECOUNT");
                return heartCount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;
    }

//    public static class BookDAO {
//
//        private Connection conn = null;
//        DataSource dataSource = null;
//
//        public BookDAO() {
//            try {
//                Context init = new InitialContext();
//                dataSource = (DataSource)init.lookup("java:comp/env/jdbc/mysql");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        public MypageBooksDTO getUserLendingBook() throws IOException {
//
//            ArrayList<MypageBooksDTO> userBookList = null;
//
//            String SQL = "select r.rental_no as all_rentals, b.book_name, b.book_author_name, r.scheduled_return_date, r.rental_date, r.rental_no, r.scroll_y, r.modal_width \n" +
//                    "from users u \n" +
//                    "join book_rental r\n" +
//                    "on u.user_no = r.user_no\n" +
//                    "join books b\n" +
//                    "on r.ISBN = b.ISBN \n" +
//                    "where u.user_no = 2\n" +
//                    "and r.actual_return_date is null;";
//            //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함
//
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//            userBookList = new ArrayList<>();
//            try {
//                conn = dataSource.getConnection();
//                pstmt = conn.prepareStatement(SQL);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    MypageBooksDTO books = new MypageBooksDTO(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                            rs.getString(6),
//                            rs.getInt(7),
//                            rs.getInt(8)
//                    );
//                    userBookList.add(books);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (conn != null) {conn.close();}
//                    if (pstmt != null) {pstmt.close();}
//                    if (rs != null) {rs.close();}
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            return userBookList;
//        }
//
//        public int getTotalRentals() {
//            int userTotalRentals = 0;
//
//            String SQL = "select count(*) as rental_num\n" +
//                    "from book_rental\n" +
//                    "where book_rental.user_no = 2;";
//            //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함
//
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//    //        userTotalRentals = new ArrayList<>();
//            try {
//                conn = dataSource.getConnection();
//                pstmt = conn.prepareStatement(SQL);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    MypageTotalRentalDTO totalRentals = new MypageTotalRentalDTO(
//                            rs.getInt(1)
//                    );
//                    userTotalRentals = totalRentals.getRental_num();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (conn != null) {conn.close();}
//                    if (pstmt != null) {pstmt.close();}
//                    if (rs != null) {rs.close();}
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            return userTotalRentals;
//        }
//
//        public ArrayList<MypageReturnedBooksDTO> getUserReturnedBook() {
//            ArrayList<MypageReturnedBooksDTO> userReturnedBookList = null;
//
//            String SQL = "select b.book_name, b.book_author_name, r.actual_return_date\n" +
//                    "from users u \n" +
//                    "join book_rental r\n" +
//                    "on u.user_no = r.user_no\n" +
//                    "join books b\n" +
//                    "on r.ISBN = b.ISBN\n" +
//                    "where u.user_no = 2\n" +
//                    "and r.actual_return_date is not null;";
//            //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함
//
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//            userReturnedBookList = new ArrayList<>();
//            try {
//                conn = dataSource.getConnection();
//                pstmt = conn.prepareStatement(SQL);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    MypageReturnedBooksDTO books = new MypageReturnedBooksDTO(
//                            rs.getString(1),
//                            rs.getString(2),
//                            rs.getString(3)
//                    );
//                    userReturnedBookList.add(books);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (conn != null) {conn.close();}
//                    if (pstmt != null) {pstmt.close();}
//                    if (rs != null) {rs.close();}
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            return userReturnedBookList;
//        }
//
//        public BookDTO getLendingBookContent() throws IOException {
//
//            //txt파일 가져올 때
//    //        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\user\\Desktop\\book.txt"));
//    //
//    //        List<String> bookText = new ArrayList<>();
//    //
//    //        for (String line : lines) {
//    //            String word = line.replace("\\n", "<br>");
//    //            bookText.add(word);
//    //        }
//
//            BookDTO bookDTO = new BookDTO();
//
//            String SQL = "select books.book_content\n" +
//                    "from books\n" +
//                    "join book_rental\n" +
//                    "on books.ISBN = book_rental.ISBN\n" +
//                    "join users\n" +
//                    "on users.user_no = book_rental.user_no\n" +
//                    "where users.user_no = 2;";
//            //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함
//
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//            try {
//                conn = dataSource.getConnection();
//                pstmt = conn.prepareStatement(SQL);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    bookDTO.setBookContent(rs.getString("book_content"));
//                }
//                DBUtil.close(rs, pstmt, conn);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return bookDTO;
//
//        }
//
//        public int saveBookScrollY(int modalY, int modalWidth, int rental_no) {
//
//            String SQL = "";
//
//            System.out.println("MODAL WIDTH: " + modalWidth);
//
//            SQL = "update book_rental set scroll_y = ?, modal_width = ? where rental_no = 2;";
//
//            System.out.println("SQL: " + SQL);
//
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//
//            try {
//
//                conn = dataSource.getConnection();
//                pstmt = conn.prepareStatement(SQL);
//                pstmt.setInt(1, modalY);
//                pstmt.setInt(2, modalWidth);
//                System.out.println("PSTMT : " + pstmt);
//                return pstmt.executeUpdate();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (conn != null) { conn.close();}
//                    if (pstmt != null) { pstmt.close();}
//                    if (rs != null) {rs.close();}
//                } catch (Exception e){e.printStackTrace();}
//            }
//            return -1;
//        }
//
//    }

}
