package com.sajan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    static Connection con;

    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost/takeodb";
    private static final String username="sajan";
    private static final String password="enter@123";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, username, password);
        return con;
    }

    public static void closeConnection() throws SQLException {
        if(con != null){
            con.close();
            con.clearWarnings();
        }
    }
}
