package com.daraz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDAO {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost/daraz";
    protected static Connection connection = null;

    public GenericDAO() {
        if (connection == null) {
            configureConnection();
        }
    }

    private void configureConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void closeResources(PreparedStatement preStmt) {
        if (preStmt != null) {
            try {
                preStmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void closeResources(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void closeResources(PreparedStatement preStmt, ResultSet rs) {
        closeResources(rs);
        closeResources(preStmt);
    }

    protected long lastInsertId(PreparedStatement preStmt) {
        ResultSet rs = null;
        long lastInsertId = 0;
        try {
            rs = preStmt.getGeneratedKeys();
            rs.next();
            lastInsertId = rs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(rs);
        }
        return lastInsertId;
    }

    public static void setAutoCommit(boolean flag) {
        try {
            connection.setAutoCommit(flag);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
