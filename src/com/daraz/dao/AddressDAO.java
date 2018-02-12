package com.daraz.dao;

import com.daraz.bean.AddressBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDAO extends GenericDAO {
    
    public long create(AddressBean address) {
        String sql = "INSERT INTO address ("
                + "street, city, state, country, zip, created_date, modified_date"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, address.getStreet());
            preStmt.setString(2, address.getCity());
            preStmt.setString(3, address.getState());
            preStmt.setString(4, address.getCountry());
            preStmt.setString(5, address.getZip());
            preStmt.setString(6, address.getCreatedDate());
            preStmt.setString(7, address.getModifiedDate());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public AddressBean readById(long id) {
        String sql = "SELECT * "
                + "FROM address "
                + "WHERE id = ?";
        AddressBean address = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                address = new AddressBean();
                address.setId(rs.getLong("id"));
                address.setStreet(rs.getString("street"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                address.setZip(rs.getString("zip"));
                address.setCreatedDate(rs.getString("created_date"));
                address.setModifiedDate(rs.getString("modified_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return address;
    }
    
    public boolean update(AddressBean address) {
        String sql = "UPDATE address SET "
                + "street = ?, "
                + "city = ?, "
                + "state = ?, "
                + "country = ?, "
                + "zip = ?, "
                + "modified_date = ? "
                + "WHERE id = ?";
        boolean flag = false;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, address.getStreet());
            preStmt.setString(2, address.getCity());
            preStmt.setString(3, address.getState());
            preStmt.setString(4, address.getCountry());
            preStmt.setString(5, address.getZip());
            preStmt.setString(6, address.getModifiedDate());
            preStmt.setLong(7, address.getId());
            flag =  preStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
}