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
            String sql ="SELECT b.isbn, b.book_name, b.publisher_name, a.author_name FROM books b inner join authors a on b.author_no = a.author_no;";
            pstmt  = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                BookDTO bookDTO = new BookDTO();
                bookDTO.setISBN(rs.getString("ISBN"));
                bookDTO.setBookname(rs.getString("book_name"));
                bookDTO.setPublisher(rs.getString("publisher_name"));
                bookDTO.setAuther(rs.getString("author_name"));
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
            throw new RuntimeException(e);
        }
        return categories;
    }

    public int uploadBook(BookDTO dto){
        Connection conn = null;
        int count = 0;
        try {
            conn = dataSource.getConnection();
            String sql ="";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getISBN());
            pstmt.setString(2, dto.getBookname());
            pstmt.setString(3, dto.getAuther());
            rs = pstmt.executeQuery();



        }catch (SQLException e){

        }
        return count;

    }

    public BookDTO deleteBook(String isbn){

        Connection conn = null;
        BookDTO bookDTO = new BookDTO();

        try {
            conn = dataSource.getConnection();
            String sql ="SELECT b.isbn, b.book_name, b.publisher_name, a.author_name, b.book_pub_date, b.book_info, b.book_chapter, b.book_content FROM books b inner join authors a on b.author_no = a.author_no where b.isbn = ? ";
            pstmt  = conn.prepareStatement(sql);

            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookDTO.setISBN(rs.getString("isbn"));
                bookDTO.setBookname(rs.getString("book_name"));
                bookDTO.setAuther(rs.getString("author_name"));
                bookDTO.setPublisher(rs.getString("publisher_name"));
                bookDTO.setPubdate(rs.getString("book_pub_date"));
                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));
                bookDTO.setBookinfo(rs.getString("book_info"));
                bookDTO.setBookchapter(rs.getString("book_chapter"));
                bookDTO.setBookcontent(rs.getString("book_content"));
            }
            pstmt.close();
            conn.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

}
