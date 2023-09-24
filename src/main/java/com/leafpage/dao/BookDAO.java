package com.leafpage.dao;

import com.leafpage.dto.BookDTO;
import com.leafpage.dto.MypageBooksDTO;
import com.leafpage.dto.MypageReturnedBooksDTO;
import com.leafpage.util.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BookDAO {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = null;


    public List<BookDTO> booklist(int pageNum, int amount) {


        List<BookDTO> bookDTOList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT B.isbn, B.book_name, B.book_publisher_name, B.book_author_name " +
                    "FROM (SELECT ROW_NUMBER() OVER (ORDER BY b.book_name) AS rn, b.isbn, b.book_name, b.book_publisher_name, b.book_author_name FROM books b) B " +
                    "WHERE rn > ? AND rn <= ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ((pageNum - 1) * amount));
            pstmt.setInt(2, (pageNum * amount));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setISBN(rs.getString("isbn"));
                bookDTO.setBookName(rs.getString("book_name"));
                bookDTO.setBookPublisherName(rs.getString("book_publisher_name"));
                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));


                bookDTOList.add(bookDTO);
            }
            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
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


    public int getTotal() {
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "select count(*) as total from books";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("total");
            }

            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }


    public int uploadBook(BookDTO dto) {

        int count = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO books(ISBN, book_name, book_author_name, book_img, book_info, book_publisher_name, book_pub_date, book_upload_date, book_content, book_chapter, book_views, book_state)values (?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, 0, 0) ";
            pstmt = conn.prepareStatement(sql);
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
            DBUtil.close(rs, pstmt, conn);

        } catch (SQLException e) {
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


    private void insertCategories(Connection conn, BookDTO dto) {
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


    public BookDTO detailBook(String isbn) {


        BookDTO bookDTO = new BookDTO();

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT b.isbn, b.book_name, b.book_publisher_name, b.book_author_name, b.book_pub_date, b.book_info, b.book_chapter, b.book_content, b.book_img FROM books b where b.isbn = ? ";
            pstmt = conn.prepareStatement(sql);

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
            DBUtil.close(rs, pstmt, conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    public int bookDelete(BookDTO dto) {
        int count = 0;

        List<BookDTO> bookDTOList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE books SET book_state = 1 WHERE ISBN = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getISBN());
            count = pstmt.executeUpdate();
            DBUtil.close(rs, pstmt, conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return count;
    }

    public int updateBook(BookDTO dto) {

        int count = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "UPDATE books SET  book_name = ?, book_author_name = ?, book_img = ?, book_info = ?, book_publisher_name = ?, book_pub_date = ?, book_content = ?, book_chapter = ? WHERE ISBN = ?";
            pstmt = conn.prepareStatement(sql);
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
            DBUtil.close(rs, pstmt, conn);
        } catch (SQLException e) {
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


    private void updateCategories(Connection conn, BookDTO dto) {
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

    public BookDTO getBookDetails(String isbn) {

        BookDTO bookDetails = new BookDTO();

        String SQL = "select b.ISBN, b.book_name, b.book_author_name, b.book_img, b.book_info, b.book_publisher_name, b.book_pub_date, b.book_chapter, c.category_name\n" +
                "from books b\n" +
                "join book_category c\n" +
                "on b.ISBN = c.ISBN\n" +
                "where b.ISBN = ?;";

        try {

            conn = DBUtil.getConnection();
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                bookDetails.setISBN(rs.getString("ISBN"));
                bookDetails.setBookName(rs.getString("book_name"));
                bookDetails.setBookAuthorName(rs.getString("book_author_name"));
                bookDetails.setBookImg(rs.getString("book_img"));
                bookDetails.setBookInfo(rs.getString("book_info"));
                bookDetails.setBookPublisherName(rs.getString("book_publisher_name"));
                bookDetails.setBookPubDate(rs.getString("book_pub_date"));
                bookDetails.setBookChapter(rs.getString("book_chapter"));
                bookDetails.setCategories(findCategories(conn, bookDetails.getISBN()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return bookDetails;
    }

    public List<BookDTO> findSameAuthorBooks(String isbn) {

        List<BookDTO> sameAuthorBooks = new ArrayList<>();

        String authorName = findAuthorName(isbn);

        String SQL = "select ISBN, book_name, book_img, book_publisher_name\n" +
                "from books\n" +
                "where book_author_name = ?;";

        try {
            conn = DBUtil.getConnection();
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, authorName);
            rs = pstmt.executeQuery();


            while (rs.next()) {
                BookDTO sameAuthorBook = new BookDTO();
                sameAuthorBook.setBookName(rs.getString("book_name"));
                sameAuthorBook.setBookImg(rs.getString("book_img"));
                sameAuthorBook.setBookPublisherName(rs.getString("book_publisher_name"));
                sameAuthorBook.setISBN(rs.getString("ISBN"));
                sameAuthorBooks.add(sameAuthorBook);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return sameAuthorBooks;
    }

    public String findAuthorName(String ISBN) {

        String authorName = "";

        String SQL = "select book_author_name \n" +
                "from books\n" +
                "where ISBN = ?;";

        try {
            conn = DBUtil.getConnection();
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, ISBN);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                authorName = rs.getString("book_author_name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return authorName;
    }

    public List<MypageBooksDTO> getUserLendingBook(Long userNo) throws IOException {

        List<MypageBooksDTO> userBookList = new ArrayList<>();

        String SQL = "select b.book_img, b.book_name, b.book_author_name, r.scheduled_return_date, r.rental_date, r.rental_no, r.scroll_y, r.modal_width, b.book_content \n" +
                "from users u \n" +
                "join book_rental r\n" +
                "on u.user_no = r.user_no\n" +
                "join books b\n" +
                "on r.ISBN = b.ISBN \n" +
                "where u.user_no = ?\n" +
                "and r.actual_return_date is null;";
        //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, userNo);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MypageBooksDTO books = new MypageBooksDTO();
                books.setBookName(rs.getString("book_name"));
                books.setBookAuthorName(rs.getString("book_author_name"));
                books.setScheduledReturnDate(rs.getString("scheduled_return_date"));
                books.setRentalDate(rs.getString("rental_date"));
                books.setRentalNo(rs.getString("rental_no"));
                books.setScrollY(rs.getInt("scroll_y"));
                books.setModalWidth(rs.getInt("modal_width"));
                books.setBookContent(rs.getString("book_content"));
                books.setBookImg(rs.getString("book_img"));

                userBookList.add(books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return userBookList;
    }

    public List<MypageReturnedBooksDTO> getUserReturnedBook(Long userNo) {

        ArrayList<MypageReturnedBooksDTO> userReturnedBookList = new ArrayList<>();

        String SQL = "select b.book_name, b.book_author_name, b.book_img, r.actual_return_date\n" +
                "from users u \n" +
                "join book_rental r\n" +
                "on u.user_no = r.user_no\n" +
                "join books b\n" +
                "on r.ISBN = b.ISBN\n" +
                "where u.user_no = ?\n" +
                "and r.actual_return_date is not null;";
        //user_no는 이후 로그인 기능 붙였을 때 파라미터로 받아야 함

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, userNo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MypageReturnedBooksDTO books = new MypageReturnedBooksDTO();
                books.setBookName(rs.getString("book_name"));
                books.setBookAuthorName(rs.getString("book_author_name"));
                books.setActualReturnDate(rs.getString("actual_return_date"));
                books.setBookImg(rs.getString("book_img"));
                userReturnedBookList.add(books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return userReturnedBookList;
    }

    public int saveBookScrollY(int modalY, int modalWidth, int rentalNo) {

        String SQL = "update book_rental set scroll_y = ?, modal_width = ? where rental_no = ?;";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, modalY);
            pstmt.setInt(2, modalWidth);
            pstmt.setInt(3, rentalNo);
            log.debug("CHECK SAVE Y : {} " + pstmt);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return -1;
    }

    public List<BookDTO> findMainBooks() {

        List<BookDTO> mainBooks = new ArrayList<>();


        String SQL = "select ISBN , book_name, book_img, book_author_name, book_views\n" +
                "from books\n" +
                "where book_state = 0\n" +
                "order by book_views desc\n" +
                "limit 8;";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setBookName(rs.getString("book_name"));
                bookDTO.setBookImg(rs.getString("book_img"));
                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
                bookDTO.setISBN(rs.getString("ISBN"));
                mainBooks.add(bookDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return mainBooks;

    }

    public List<BookDTO> booksearchlist(int pageNum, int amount, String keyword) {


        List<BookDTO> bookDTOList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = " SELECT B.isbn, B.book_name, B.book_publisher_name, B.book_author_name " +
                    "FROM (SELECT ROW_NUMBER() OVER (ORDER BY book_name) AS rn, isbn, book_name,book_author_name, book_publisher_name " +
                    "from books WHERE concat(isbn, book_name, book_author_name, book_publisher_name ) LIKE ? ) B WHERE rn > ? AND rn <= ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setInt(2, ((pageNum - 1) * amount));
            pstmt.setInt(3, (pageNum * amount));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setISBN(rs.getString("isbn"));
                bookDTO.setBookName(rs.getString("book_name"));
                bookDTO.setBookPublisherName(rs.getString("book_publisher_name"));
                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));


                bookDTOList.add(bookDTO);
            }
            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookDTOList;
    }

    public int getsearchTotal(String keyword) {
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT count(*) as total" +
                    " FROM (SELECT ROW_NUMBER() OVER (ORDER BY book_name) AS rn, isbn, book_name,book_author_name, book_publisher_name " +
                    "from books WHERE concat(isbn, book_name, book_author_name, book_publisher_name ) LIKE ? ) B;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");

            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("total");
            }

            DBUtil.close(rs, pstmt, conn);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }


    public List<BookDTO> searchBooks(String searchSelect, String searchKeyword, String page) {

        List<BookDTO> books = new ArrayList<>();

        String SQL = "select ISBN, book_name, book_img, book_author_name, book_views, book_upload_date\n" +
                "from books\n" +
                "where book_state = 0\n";

        if (searchSelect.equals("출판사")) {
            SQL += " and book_publisher_name LIKE ?";
        }
        if (searchSelect.equals("제목")) {
            SQL += " and book_name LIKE ?";
        }
        if (searchSelect.equals("작가")) {
            SQL += " and book_author_name LIKE ?";
        }
        if (searchSelect.equals("전체")) {
            SQL += " and concat(book_publisher_name, book_name, book_author_name) LIKE ?";
        }

        SQL += " order by book_upload_date desc limit 12 offset 0;";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, "%" + searchKeyword + "%");
            log.debug("CHECK SEARCH QUERY : {}", pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setISBN(rs.getString("ISBN"));
                bookDTO.setBookName(rs.getString("book_name"));
                bookDTO.setBookImg(rs.getString("book_img"));
                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
                books.add(bookDTO);
            }

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return books;
    }

    public List<BookDTO> sortBooks(String sortWord, String searchSelect, String searchKeyword, String genre, int page) {

        List<BookDTO> books = new ArrayList<>();

        String SQL = "select distinct books.ISBN, book_name, book_img, book_author_name, book_views, book_upload_date\n" +
                "from books\n" +
                "join book_category\n" +
                "on book_category.ISBN = books.ISBN\n" +
                "where book_state = 0 \n";

        String SQLView = "order by book_views desc ";

        String SQLDate = "order by book_upload_date desc ";

        if (!genre.isEmpty()) {
            SQL += "and book_category.category_name LIKE ?";
        }

        if (searchSelect.equals("출판사")) {

            SQL += "and book_publisher_name LIKE ? ";

            if (sortWord.equals("인기순")) {
                SQL += SQLView;
            }
            else  {
                SQL += SQLDate;
            }
        }
        if (searchSelect.equals("제목")) {

            SQL += "and book_name LIKE ? ";

            if (sortWord.equals("인기순")) {
                SQL += SQLView;
            }
            else {
                SQL += SQLDate;
            }
        }
        if (searchSelect.equals("작가")) {

            SQL += "and book_author_name LIKE ? ";

            if (sortWord.equals("인기순")) {
                SQL += SQLView;
            }
            else {
                SQL += SQLDate;
            }
        }
        if (searchSelect.equals("전체")) {

            SQL += "and concat(book_publisher_name, book_name, book_author_name) LIKE ? ";

            if (sortWord.equals("인기순")) {
                SQL += SQLView;
            }
            else {
                SQL += SQLDate;
            }
        }

        SQL += "limit 12 offset ?;";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);

            if (genre.isEmpty()){
                pstmt.setString(1, "%" + searchKeyword + "%");
                pstmt.setInt(2, page);
//                pstmt.setInt(3, pageNum);
            }
            if (!genre.isEmpty()) {
                pstmt.setString(1, "%" + genre + "%");
                pstmt.setString(2, "%" + searchKeyword + "%");
                pstmt.setInt(3, page);
//                pstmt.setInt(4, pageNum);
            }

            log.debug("CHECK SEARCH RESULT QUERY : {} " + pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setISBN(rs.getString("ISBN"));
                bookDTO.setBookName(rs.getString("book_name"));
                bookDTO.setBookImg(rs.getString("book_img"));
                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
                books.add(bookDTO);
            }

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        log.debug("END OF SORT : {} " + books);
        return books;
    }


    public boolean isISBNZero(String isbn) {

        String SQL = "select ISBN\n" +
                "from books\n" +
                "where ISBN = ?\n" +
                "and book_state = 0;";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, isbn);
            System.out.println("CHECK QUERY : " + pstmt);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return false;
    }


//    public List<BookDTO> booksearchlist(int pageNum, int amount, String keyword){
//
//
//        List<BookDTO> bookDTOList= new ArrayList<>();
//        try {
//            conn = DBUtil.getConnection();
//            String sql = " SELECT B.isbn, B.book_name, B.book_publisher_name, B.book_author_name " +
//                    "FROM (SELECT ROW_NUMBER() OVER (ORDER BY book_name) AS rn, isbn, book_name,book_author_name, book_publisher_name " +
//                    "from books WHERE concat(isbn, book_name, book_author_name, book_publisher_name ) LIKE ? ) B WHERE rn > ? AND rn <= ?;";
//
//            pstmt  = conn.prepareStatement(sql);
//            pstmt.setString(1,"%" + keyword + "%");
//            pstmt.setInt(2, ((pageNum - 1)* amount));
//            pstmt.setInt(3, (pageNum * amount));
//            rs = pstmt.executeQuery();
//            while (rs.next()){
//                BookDTO bookDTO = new BookDTO();
//                bookDTO.setISBN(rs.getString("isbn"));
//                bookDTO.setBookName(rs.getString("book_name"));
//                bookDTO.setBookPublisherName(rs.getString("book_publisher_name"));
//                bookDTO.setBookAuthorName(rs.getString("book_author_name"));
//                bookDTO.setCategories(findCategories(conn, bookDTO.getISBN()));
//
//
//                bookDTOList.add(bookDTO);
//            }
//            DBUtil.close(rs ,pstmt, conn);
//
//
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//        return bookDTOList;
//    }

//    public int  getsearchTotal(String keyword){
//        int result  = 0;
//
//        try {
//            conn = DBUtil.getConnection();
//            String sql = "SELECT count(*) as total" +
//                    " FROM (SELECT ROW_NUMBER() OVER (ORDER BY book_name) AS rn, isbn, book_name,book_author_name, book_publisher_name " +
//                    "from books WHERE concat(isbn, book_name, book_author_name, book_publisher_name ) LIKE ? ) B;";
//
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1,"%" + keyword + "%");
//
//            rs = pstmt.executeQuery();
//
//            if(rs.next()) {
//                result = rs.getInt("total");
//            }
//
//            DBUtil.close(rs ,pstmt, conn);
//
//
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//
//        return result ;
//    }

    public boolean duplicationISBN(String ISBN){


        try {
            conn = DBUtil.getConnection();
            String sql = "select isbn from books where ISBN = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,ISBN);

            rs = pstmt.executeQuery();

            if(rs.next()) {
               return true;
            }

            DBUtil.close(rs ,pstmt, conn);


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
