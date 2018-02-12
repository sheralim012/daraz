package com.daraz.dao;

import com.daraz.bean.BuyerBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyerDAO extends GenericDAO {
    
    public long create(BuyerBean buyer) {
        String sql = "INSERT INTO buyer ("
                + "first_name, last_name, contact_no, email, created_date, modified_date"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, buyer.getFirstName());
            preStmt.setString(2, buyer.getLastName());
            preStmt.setString(3, buyer.getContactNo());
            preStmt.setString(4, buyer.getEmail());
            preStmt.setString(5, buyer.getCreatedDate());
            preStmt.setString(6, buyer.getModifiedDate());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuyerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public BuyerBean readById(long id) {
        String sql = "SELECT * "
                + "FROM buyer "
                + "WHERE id = ?";
        BuyerBean buyer = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                buyer = new BuyerBean();
                buyer.setId(rs.getLong("id"));
                buyer.setFirstName(rs.getString("first_name"));
                buyer.setLastName(rs.getString("last_name"));
                buyer.setContactNo(rs.getString("contact_no"));
                buyer.setEmail(rs.getString("email"));
                buyer.setCreatedDate(rs.getString("created_date"));
                buyer.setModifiedDate(rs.getString("modified_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuyerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return buyer;
    }
    
    public boolean update(BuyerBean buyer) {
        String sql = "UPDATE buyer SET "
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
            preStmt.setString(1, buyer.getFirstName());
            preStmt.setString(2, buyer.getLastName());
            preStmt.setString(3, buyer.getContactNo());
            preStmt.setString(4, buyer.getEmail());
            preStmt.setString(5, buyer.getModifiedDate());
            preStmt.setLong(6, buyer.getId());
            flag =  preStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(BuyerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
}