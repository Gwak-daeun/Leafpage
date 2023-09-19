package com.leafpage.dao;

import com.leafpage.dto.BookDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    DataSource dataSource = null;

    PreparedStatement pstmt = null;

    ResultSet rs = null;

    //생성자에서 DB연결 설정
    public BookDAO() {
        try {
            Context init = new InitialContext();
            dataSource = (DataSource)init.lookup("java:comp/env/jdbc/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BookDTO> booklist(){

        Connection conn = null;
        List<BookDTO> bookDTOList= new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            String sql ="SELECT b.isbn, b.book_name, b.book_publisher_name, b.book_author_name FROM books b";
            pstmt  = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                BookDTO bookDTO = new BookDTO();
                bookDTO.setISBN(rs.getString("ISBN"));
                bookDTO.setBookname(rs.getString("book_name"));
                bookDTO.setPublisher(rs.getString("book_publisher_name"));
                bookDTO.setAuther(rs.getString("book_author_name"));
                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));

                bookDTOList.add(bookDTO);
            }
            pstmt.close();
            conn.close();

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
            PreparedStatement statement = conn.prepareStatement(sql);

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
        Connection conn = null;
        int count = 0;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);


            String sql ="INSERT INTO books(ISBN, book_name, book_author_name, book_img, book_info, book_publisher_name, book_pub_date, book_upload_date, book_content, book_chapter, book_views, book_state)values (?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, 0, 0) ";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getISBN());
            pstmt.setString(2, dto.getBookname());
            pstmt.setString(3, dto.getAuther());
            pstmt.setString(4, dto.getBookimgFullPath());
            pstmt.setString(5, dto.getBookinfo());
            pstmt.setString(6, dto.getPublisher());
            pstmt.setString(7, dto.getPubdate());
            pstmt.setString(8, dto.getBookchapter());
            pstmt.setString(9, dto.getBookcontent());
            count = pstmt.executeUpdate();
            pstmt.close();
            insertCategories(conn, dto);

            conn.commit();
            conn.close();

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

        Connection conn = null;
        BookDTO bookDTO = new BookDTO();

        try {
            conn = dataSource.getConnection();
            String sql ="SELECT b.isbn, b.book_name, b.book_publisher_name, b.book_author_name, b.book_pub_date, b.book_info, b.book_chapter, b.book_content, b.book_img FROM books b where b.isbn = ? ";
            pstmt  = conn.prepareStatement(sql);

            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookDTO.setISBN(rs.getString("isbn"));
                bookDTO.setBookname(rs.getString("book_name"));
                bookDTO.setAuther(rs.getString("book_author_name"));
                bookDTO.setPublisher(rs.getString("book_publisher_name"));
                bookDTO.setPubdate(rs.getString("book_pub_date"));
                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));
                bookDTO.setBookinfo(rs.getString("book_info"));
                bookDTO.setBookchapter(rs.getString("book_chapter"));
                bookDTO.setBookcontent(rs.getString("book_content"));
                bookDTO.setBookimgFullPath(rs.getString("book_img"));
            }
            pstmt.close();
            conn.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    public int bookDelete(BookDTO dto){
        int count = 0;
        Connection conn = null;
        List<BookDTO> bookDTOList= new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            String sql ="UPDATE books SET book_state = 1 WHERE ISBN = ?";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getISBN());
            count = pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return count;
    }

    public int updateBook(BookDTO dto){
        Connection conn = null;
        int count = 0;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);


            String sql ="UPDATE books SET  book_name = ?, book_author_name = ?, book_img = ?, book_info = ?, book_publisher_name = ?, book_pub_date = ?, book_content = ?, book_chapter = ? WHERE ISBN = ?";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getBookname());
            pstmt.setString(2, dto.getAuther());
            pstmt.setString(3, dto.getBookimgFullPath());
            pstmt.setString(4, dto.getBookinfo());
            pstmt.setString(5, dto.getPublisher());
            pstmt.setString(6, dto.getPubdate());
            pstmt.setString(7, dto.getBookchapter());
            pstmt.setString(8, dto.getBookcontent());
            pstmt.setString(9, dto.getISBN());
            count = pstmt.executeUpdate();
            pstmt.close();
            updateCategories(conn, dto);

            conn.commit();
            conn.close();

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
