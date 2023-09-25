package com.leafpage.dao;

import com.leafpage.common.exception.RollbackException;
import com.leafpage.dto.RentalDTO;
import com.leafpage.util.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RentalDAO {

    private static final String SELECT_RENTAL_COUNT =
            "SELECT COUNT(*) AS RENTAL_COUNT " +
                    "FROM book_rental " +
                    "WHERE USER_NO = ? AND ACTUAL_RETURN_DATE IS NULL";

    private static final String SELECT_RENTING_BOOK =
            "SELECT ACTUAL_RETURN_DATE FROM book_rental WHERE ISBN = ? AND ACTUAL_RETURN_DATE IS NULL";

    private static final String INSERT_RENTAL =
            "INSERT INTO book_rental(USER_NO, ISBN, RENTAL_DATE, SCHEDULED_RETURN_DATE) " +
                    "VALUES(?, ?, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 6 DAY))";

    private static final String UPDATE_INCREASE_VIEW_COUNT =
            "UPDATE books SET book_views = book_views + 1 WHERE ISBN = ?";

    private static final String SELECT_NOT_YET_RETURNED_BOOK =
            "SELECT ACTUAL_RETURN_DATE " +
                    "FROM book_rental " +
                    "WHERE USER_NO = ? AND RENTAL_NO = ? AND ACTUAL_RETURN_DATE IS NULL";
    private static final String UPDATE_RENTAL =
            "UPDATE book_rental SET ACTUAL_RETURN_DATE = CURRENT_DATE WHERE USER_NO = ? AND RENTAL_NO = ?";
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public void rentBook(RentalDTO dto) {
        try {
            connect();

            statement = connection.prepareStatement(INSERT_RENTAL);
            statement.setLong(1, dto.getUserNo());
            statement.setString(2, dto.getIsbn());
            statement.executeUpdate();
            log.debug("{}", statement);

            increaseView(dto.getIsbn());

            connection.commit();
        } catch (SQLException e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            rollback(methodName, e.getMessage());
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }
    }

    private void increaseView(String isbn) throws SQLException {
        statement = connection.prepareStatement(UPDATE_INCREASE_VIEW_COUNT);
        statement.setString(1, isbn);
        statement.executeUpdate();
        log.debug("{}", statement);
    }

    public int findRentalCount(RentalDTO dto) {
        int rentalCount = 0;
        try {
            connect();

            statement = connection.prepareStatement(SELECT_RENTAL_COUNT);
            statement.setLong(1, dto.getUserNo());
            resultSet = statement.executeQuery();
            log.debug("{}", statement);

            if (resultSet.next()) {
                rentalCount = resultSet.getInt("RENTAL_COUNT");
            }

        } catch (SQLException e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            rollback(methodName, e.getMessage());
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }
        System.out.println("rentalCount = " + rentalCount);
        return rentalCount;
    }

    public boolean findRenting(RentalDTO dto) {
        boolean result = false;
        try {
            connect();
            statement = connection.prepareStatement(SELECT_RENTING_BOOK);
            statement.setString(1, dto.getIsbn());
            resultSet = statement.executeQuery();
            log.debug("{}", statement);

            result = resultSet.next();
        } catch (SQLException e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            rollback(methodName, e.getMessage());
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }
        return result;
    }

    public void returnBook(RentalDTO dto) {
        try {
            connect();

            statement = connection.prepareStatement(UPDATE_RENTAL);
            statement.setLong(1, dto.getUserNo());
            statement.setLong(2, dto.getRentalNo());
            statement.executeUpdate();
            log.debug("{}", statement);

            connection.commit();
        } catch (SQLException e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            rollback(methodName, e.getMessage());
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }
    }

    public boolean isBookReturnable(RentalDTO dto) {
        boolean canReturn = false;
        try {
            connect();

            statement = connection.prepareStatement(SELECT_NOT_YET_RETURNED_BOOK);
            statement.setLong(1, dto.getUserNo());
            statement.setLong(2, dto.getRentalNo());
            resultSet = statement.executeQuery();
            log.debug("{}", statement);

            canReturn = resultSet.next();
        } catch (SQLException e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            rollback(methodName, e.getMessage());
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }
        return canReturn;
    }

    private void connect() throws SQLException {
        connection = DBUtil.getConnection();
        connection.setAutoCommit(false);
    }

    private void rollback(String methodName, String errorMessage) {
        String className = new Object() {
        }.getClass().getEnclosingClass().getName();

        try {
            log.error("{}의 {} 실패", className, methodName);
            log.error("{}", errorMessage);
            connection.rollback();
        } catch (SQLException ex) {
            log.error("{}의 {} 롤백 실패", className, methodName);
            log.error("{}", ex.getMessage());
            throw new RollbackException(className, methodName);
        }
    }

    public List<String> returnOverdueBooks(int userNo) {

        List<String> returnedBooksISBN = new ArrayList<>();

        List<RentalDTO> overdueBooks = new BookDAO().findOverdueBooks(userNo);

        for (RentalDTO overdueBook : overdueBooks) {
            returnBook(overdueBook);
            returnedBooksISBN.add(overdueBook.getIsbn());
        }

        return returnedBooksISBN;
    }
}
