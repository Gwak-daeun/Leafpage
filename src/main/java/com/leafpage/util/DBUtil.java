package com.leafpage.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource DATA_SOURCE;

    static {
        Context init = null;
        try {
            init = new InitialContext();
            DATA_SOURCE = (DataSource)init.lookup("java:comp/env/jdbc/mysql");

        } catch (NamingException e) {
            throw new IllegalArgumentException("데이터베이스를 찾을 수 없습니다.");

        }
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException("데이터베이스와의 연결이 실패했습니다.");
        }

        return connection;
    }

    public static void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new IllegalArgumentException("ResultSet 을 닫는 것에 실패했습니다.");
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Statement 을 닫는 것에 실패했습니다.");
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Connection 을 닫는 것에 실패했습니다.");
            }
        }
    }
}