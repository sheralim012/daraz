package com.daraz.dao;

import com.daraz.bean.UserBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends GenericDAO {
    
    public long create(UserBean user) {
        String sql = "INSERT INTO user ("
                + "username, pwd, created_date, modified_date, role, role_id, address_id"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, user.getUsername());
            preStmt.setString(2, user.getPwd());
            preStmt.setString(3, user.getCreatedDate());
            preStmt.setString(4, user.getModifiedDate());
            preStmt.setByte(5, user.getRole());
            preStmt.setLong(6, user.getRoleId());
            preStmt.setLong(7, user.getAddressId());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public UserBean readById(long id) {
        String sql = "SELECT * "
                + "FROM user "
                + "WHERE id = ?";
        UserBean user = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                user = new UserBean();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPwd(rs.getString("pwd"));
                user.setCreatedDate(rs.getString("created_date"));
                user.setModifiedDate(rs.getString("modified_date"));
                user.setRole(rs.getByte("role"));
                user.setRoleId(rs.getLong("role_id"));
                user.setAddressId(rs.getLong("address_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return user;
    }
    
    public UserBean readByUsername(String username) {
        String sql = "SELECT * "
                + "FROM user "
                + "WHERE username = ?";
        UserBean user = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, username);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                user = new UserBean();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPwd(rs.getString("pwd"));
                user.setCreatedDate(rs.getString("created_date"));
                user.setModifiedDate(rs.getString("modified_date"));
                user.setRole(rs.getByte("role"));
                user.setRoleId(rs.getLong("role_id"));
                user.setAddressId(rs.getLong("address_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return user;
    }
    
    public boolean update(UserBean user) {
        String sql = "UPDATE user SET "
                + "username = ?, "
                + "pwd = ?, "
                + "modified_date = ? "
                + "WHERE id = ?";
        boolean flag = false;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, user.getUsername());
            preStmt.setString(2, user.getPwd());
            preStmt.setString(3, user.getModifiedDate());
            preStmt.setLong(4, user.getId());
            flag =  preStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
    public boolean duplicateUsername(String username) {
        return readByUsername(username) != null;
    }
    
    public boolean duplicateUsername(String username, long id) {
        String sql = "SELECT COUNT(*) "
                + "FROM user "
                + "WHERE username = ? "
                + "AND id != ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, username);
            preStmt.setLong(2, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count > 0;
    }
    
}