package com.daraz.dao;

import com.daraz.bean.ManufacturerBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManufacturerDAO extends GenericDAO {
    
    public long create(ManufacturerBean manufacturer) {
        String sql = "INSERT INTO manufacturer ("
                + "manufacturer_name, visibility, created_date, modified_date, admin_id"
                + ") VALUES ("
                + "?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, manufacturer.getManufacturerName());
            preStmt.setByte(2, manufacturer.getVisibility());
            preStmt.setString(3, manufacturer.getCreatedDate());
            preStmt.setString(4, manufacturer.getModifiedDate());
            preStmt.setLong(5, manufacturer.getAdminId());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public ArrayList<ManufacturerBean> readAll() {
        String sql = "SELECT * "
                + "FROM manufacturer";
        ArrayList<ManufacturerBean> manufacturers = new ArrayList<>();
        ManufacturerBean manufacturer = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                manufacturer = new ManufacturerBean();
                manufacturer.setId(rs.getLong("id"));
                manufacturer.setManufacturerName(rs.getString("manufacturer_name"));
                manufacturer.setVisibility(rs.getByte("visibility"));
                manufacturer.setCreatedDate(rs.getString("created_date"));
                manufacturer.setModifiedDate(rs.getString("modified_date"));
                manufacturer.setAdminId(rs.getLong("admin_id"));
                manufacturers.add(manufacturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return manufacturers;
    }
    
    public ArrayList<ManufacturerBean> readByVisibility(byte visibility) {
        String sql = "SELECT * "
                + "FROM manufacturer "
                + "WHERE visibility = ?";
        ArrayList<ManufacturerBean> manufacturers = new ArrayList<>();
        ManufacturerBean manufacturer = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                manufacturer = new ManufacturerBean();
                manufacturer.setId(rs.getLong("id"));
                manufacturer.setManufacturerName(rs.getString("manufacturer_name"));
                manufacturer.setVisibility(rs.getByte("visibility"));
                manufacturer.setCreatedDate(rs.getString("created_date"));
                manufacturer.setModifiedDate(rs.getString("modified_date"));
                manufacturer.setAdminId(rs.getLong("admin_id"));
                manufacturers.add(manufacturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return manufacturers;
    }
    
    public ManufacturerBean readById(long id) {
        String sql = "SELECT * "
                + "FROM manufacturer "
                + "WHERE id = ?";
        ManufacturerBean manufacturer = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                manufacturer = new ManufacturerBean();
                manufacturer.setId(rs.getLong("id"));
                manufacturer.setManufacturerName(rs.getString("manufacturer_name"));
                manufacturer.setVisibility(rs.getByte("visibility"));
                manufacturer.setCreatedDate(rs.getString("created_date"));
                manufacturer.setModifiedDate(rs.getString("modified_date"));
                manufacturer.setAdminId(rs.getLong("admin_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return manufacturer;
    }
    
    public ManufacturerBean readByManufacturerName(String manufacturerName) {
        String sql = "SELECT * "
                + "FROM manufacturer "
                + "WHERE manufacturer_name = ?";
        ManufacturerBean manufacturer = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, manufacturerName);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                manufacturer = new ManufacturerBean();
                manufacturer.setId(rs.getLong("id"));
                manufacturer.setManufacturerName(rs.getString("manufacturer_name"));
                manufacturer.setVisibility(rs.getByte("visibility"));
                manufacturer.setCreatedDate(rs.getString("created_date"));
                manufacturer.setModifiedDate(rs.getString("modified_date"));
                manufacturer.setAdminId(rs.getLong("admin_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return manufacturer;
    }
    
    public ArrayList<ManufacturerBean> readLimited(int limit, int offset) {
        String sql = "SELECT * "
                + "FROM manufacturer "
                + "LIMIT ? OFFSET ?";
        ArrayList<ManufacturerBean> manufacturers = new ArrayList<>();
        ManufacturerBean manufacturer = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setInt(1, limit);
            preStmt.setInt(2, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                manufacturer = new ManufacturerBean();
                manufacturer.setId(rs.getLong("id"));
                manufacturer.setManufacturerName(rs.getString("manufacturer_name"));
                manufacturer.setVisibility(rs.getByte("visibility"));
                manufacturer.setCreatedDate(rs.getString("created_date"));
                manufacturer.setModifiedDate(rs.getString("modified_date"));
                manufacturer.setAdminId(rs.getLong("admin_id"));
                manufacturers.add(manufacturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return manufacturers;
    }
    
    public boolean update(ManufacturerBean manufacturer) {
        String sql = "UPDATE manufacturer SET "
                + "manufacturer_name = ?, "
                + "visibility = ?, "
                + "modified_date = ?, "
                + "admin_id = ? "
                + "WHERE id = ?";
        boolean flag = false;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, manufacturer.getManufacturerName());
            preStmt.setByte(2, manufacturer.getVisibility());
            preStmt.setString(3, manufacturer.getModifiedDate());
            preStmt.setLong(4, manufacturer.getAdminId());
            preStmt.setLong(5, manufacturer.getId());
            flag =  preStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
    public boolean duplicateManufacturerName(String manufacturerName) {
        return readByManufacturerName(manufacturerName) != null;
    }
    
    public boolean duplicateManufacturerName(String manufacturerName, long id) {
        String sql = "SELECT COUNT(*) "
                + "FROM manufacturer "
                + "WHERE manufacturer_name = ? "
                + "AND id != ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, manufacturerName);
            preStmt.setLong(2, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count > 0;
    }
    
    public long countAll() {
        String sql = "SELECT COUNT(*) "
                + "FROM manufacturer";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
}