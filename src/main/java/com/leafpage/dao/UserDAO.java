package com.leafpage.dao;

import com.leafpage.dto.UserDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private Connection conn = null;
    DataSource dataSource = null;

    //생성자에서 DB연결 설정
    public UserDAO() {
        try {
            Context init = new InitialContext();
            dataSource = (DataSource)init.lookup("java:comp/env/jdbc/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
