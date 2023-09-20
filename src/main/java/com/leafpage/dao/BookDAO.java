package com.leafpage.dao;

import com.leafpage.dto.BookDTO;
import com.leafpage.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = null;



    public List<BookDTO> booklist(){


        List<BookDTO> bookDTOList= new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql ="SELECT b.isbn, b.book_name, b.book_publisher_name, b.book_author_name FROM books b";
            pstmt  = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                BookDTO bookDTO = new BookDTO();
                bookDTO.setISBN(rs.getString("ISBN"));
                bookDTO.setBookName(rs.getString("book_name"));
                bookDTO.setBookPublisherName(rs.getString("book_publisher_name"));
                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));

                bookDTOList.add(bookDTO);
            }
            DBUtil.close(rs ,pstmt, conn);


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookDTOList;
    }

    private List<String> findCategories(Connection conn, String isbn) {
        List<String> categories = new ArrayList<>();

        try {
            String sql = "SELECT category_name " +
                    "FROM book_category where isbn = ?";
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);

            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("category_name");
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return categories;
    }

    public int uploadBook(BookDTO dto){

        int count = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql ="INSERT INTO books(ISBN, book_name, book_author_name, book_img, book_info, book_publisher_name, book_pub_date, book_upload_date, book_content, book_chapter, book_views, book_state)values (?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, 0, 0) ";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getISBN());
            pstmt.setString(2, dto.getBookName());
            pstmt.setString(3, dto.getBookAuthorName());
            pstmt.setString(4, dto.getBookImgFullPath());
            pstmt.setString(5, dto.getBookInfo());
            pstmt.setString(6, dto.getBookPublisherName());
            pstmt.setString(7, dto.getBookPubDate());
            pstmt.setString(8, dto.getBookChapter());
            pstmt.setString(9, dto.getBookContent());
            count = pstmt.executeUpdate();
            pstmt.close();
            insertCategories(conn, dto);

            conn.commit();
            DBUtil.close(rs ,pstmt, conn);

        }catch (SQLException e){
            System.out.println(new Object() {
            }.getClass().getEnclosingMethod().getName());
            System.out.println(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return count;

    }


    private void insertCategories(Connection conn, BookDTO dto ) {
        List<String> categories = dto.getCategories();
        int count = 0;
        try {
            String sql = "insert into book_category(category_name, ISBN) values (?, ?)";

            for (String category : categories) {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, category);
                statement.setString(2, dto.getISBN());
                System.out.println(category);
                System.out.println(dto.getISBN());
                count = statement.executeUpdate();
                statement.close();
            }
            System.out.println(count);


        } catch (SQLException e) {
            System.out.println(new Object() {
            }.getClass().getEnclosingMethod().getName());
            throw new RuntimeException(e);
        }
    }


    public BookDTO detailBook(String isbn){


        BookDTO bookDTO = new BookDTO();

        try {
            conn = DBUtil.getConnection();
            String sql ="SELECT b.isbn, b.book_name, b.book_publisher_name, b.book_author_name, b.book_pub_date, b.book_info, b.book_chapter, b.book_content, b.book_img FROM books b where b.isbn = ? ";
            pstmt  = conn.prepareStatement(sql);

            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookDTO.setISBN(rs.getString("isbn"));
                bookDTO.setBookName(rs.getString("book_name"));
                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
                bookDTO.setBookPublisherName(rs.getString("book_publisher_name"));
                bookDTO.setBookPubDate(rs.getString("book_pub_date"));
                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));
                bookDTO.setBookInfo(rs.getString("book_info"));
                bookDTO.setBookChapter(rs.getString("book_chapter"));
                bookDTO.setBookContent(rs.getString("book_content"));
                bookDTO.setBookImgFullPath(rs.getString("book_img"));
            }
            DBUtil.close(rs ,pstmt, conn);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    public int bookDelete(BookDTO dto){
        int count = 0;

        List<BookDTO> bookDTOList= new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql ="UPDATE books SET book_state = 1 WHERE ISBN = ?";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getISBN());
            count = pstmt.executeUpdate();
            DBUtil.close(rs ,pstmt, conn);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return count;
    }

    public int updateBook(BookDTO dto){

        int count = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);


            String sql ="UPDATE books SET  book_name = ?, book_author_name = ?, book_img = ?, book_info = ?, book_publisher_name = ?, book_pub_date = ?, book_content = ?, book_chapter = ? WHERE ISBN = ?";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getBookName());
            pstmt.setString(2, dto.getBookAuthorName());
            pstmt.setString(3, dto.getBookImgFullPath());
            pstmt.setString(4, dto.getBookInfo());
            pstmt.setString(5, dto.getBookPublisherName());
            pstmt.setString(6, dto.getBookPubDate());
            pstmt.setString(7, dto.getBookChapter());
            pstmt.setString(8, dto.getBookContent());
            pstmt.setString(9, dto.getISBN());
            count = pstmt.executeUpdate();
            pstmt.close();
            updateCategories(conn, dto);

            conn.commit();
            DBUtil.close(rs ,pstmt, conn);
        }catch (SQLException e){
            System.out.println(new Object() {
            }.getClass().getEnclosingMethod().getName());
            System.out.println(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return count;

    }


    private void updateCategories(Connection conn, BookDTO dto ) {
        List<String> categories = dto.getCategories();
        int count = 0;
        try {
            String sql = "delete from book_category WHERE ISBN = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dto.getISBN());
            statement.executeUpdate();
            statement.close();
            insertCategories(conn, dto);


        } catch (SQLException e) {
            System.out.println(new Object() {
            }.getClass().getEnclosingMethod().getName());
            throw new RuntimeException(e);
        }
    }

}
