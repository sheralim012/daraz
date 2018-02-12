package com.daraz.dao;

import com.daraz.bean.AdminBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO extends GenericDAO {
    
    public long create(AdminBean admin) {
        String sql = "INSERT INTO admin ("
                + "first_name, last_name, contact_no, email, created_date, modified_date"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, admin.getFirstName());
            preStmt.setString(2, admin.getLastName());
            preStmt.setString(3, admin.getContactNo());
            preStmt.setString(4, admin.getEmail());
            preStmt.setString(5, admin.getCreatedDate());
            preStmt.setString(6, admin.getModifiedDate());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public AdminBean readById(long id) {
        String sql = "SELECT * "
                + "FROM admin "
                + "WHERE id = ?";
        AdminBean admin = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                admin = new AdminBean();
                admin.setId(rs.getLong("id"));
                admin.setFirstName(rs.getString("first_name"));
                admin.setLastName(rs.getString("last_name"));
                admin.setContactNo(rs.getString("contact_no"));
                admin.setEmail(rs.getString("email"));
                admin.setCreatedDate(rs.getString("created_date"));
                admin.setModifiedDate(rs.getString("modified_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return admin;
    }
    
    public boolean update(AdminBean admin) {
        String sql = "UPDATE admin SET "
                + "first_name = ?, "
                + "last_name = ?, "
                + "contact_no = ?, "
                + "email = ?, "
                + "modified_date = ? "
                + "WHERE id = ?";
        boolean flag = false;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, admin.getFirstName());
            preStmt.setString(2, admin.getLastName());
            preStmt.setString(3, admin.getContactNo());
            preStmt.setString(4, admin.getEmail());
            preStmt.setString(5, admin.getModifiedDate());
            preStmt.setLong(6, admin.getId());
            flag =  preStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
}