package com.daraz.dao;

import com.daraz.bean.CategoryBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO extends GenericDAO {
    
    public long create(CategoryBean category) {
        String sql = "INSERT INTO category ("
                + "category_name, visibility, created_date, modified_date, admin_id"
                + ") VALUES ("
                + "?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, category.getCategoryName());
            preStmt.setByte(2, category.getVisibility());
            preStmt.setString(3, category.getCreatedDate());
            preStmt.setString(4, category.getModifiedDate());
            preStmt.setLong(5, category.getAdminId());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public ArrayList<CategoryBean> readAll() {
        String sql = "SELECT * "
                + "FROM category";
        ArrayList<CategoryBean> categories = new ArrayList<>();
        CategoryBean category = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                category = new CategoryBean();
                category.setId(rs.getLong("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setVisibility(rs.getByte("visibility"));
                category.setCreatedDate(rs.getString("created_date"));
                category.setModifiedDate(rs.getString("modified_date"));
                category.setAdminId(rs.getLong("admin_id"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return categories;
    }
    
    public ArrayList<CategoryBean> readByVisibility(byte visibility) {
        String sql = "SELECT * "
                + "FROM category "
                + "WHERE visibility = ?";
        ArrayList<CategoryBean> categories = new ArrayList<>();
        CategoryBean category = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                category = new CategoryBean();
                category.setId(rs.getLong("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setVisibility(rs.getByte("visibility"));
                category.setCreatedDate(rs.getString("created_date"));
                category.setModifiedDate(rs.getString("modified_date"));
                category.setAdminId(rs.getLong("admin_id"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return categories;
    }
    
    public CategoryBean readById(long id) {
        String sql = "SELECT * "
                + "FROM category "
                + "WHERE id = ?";
        CategoryBean category = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                category = new CategoryBean();
                category.setId(rs.getLong("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setVisibility(rs.getByte("visibility"));
                category.setCreatedDate(rs.getString("created_date"));
                category.setModifiedDate(rs.getString("modified_date"));
                category.setAdminId(rs.getLong("admin_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return category;
    }
    
    public CategoryBean readByCategoryName(String categoryName) {
        String sql = "SELECT * "
                + "FROM category "
                + "WHERE category_name = ?";
        CategoryBean category = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, categoryName);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                category = new CategoryBean();
                category.setId(rs.getLong("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setVisibility(rs.getByte("visibility"));
                category.setCreatedDate(rs.getString("created_date"));
                category.setModifiedDate(rs.getString("modified_date"));
                category.setAdminId(rs.getLong("admin_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return category;
    }
    
    public ArrayList<CategoryBean> readLimited(int limit, int offset) {
        String sql = "SELECT * "
                + "FROM category "
                + "LIMIT ? OFFSET ?";
        ArrayList<CategoryBean> categories = new ArrayList<>();
        CategoryBean category = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setInt(1, limit);
            preStmt.setInt(2, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                category = new CategoryBean();
                category.setId(rs.getLong("id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setVisibility(rs.getByte("visibility"));
                category.setCreatedDate(rs.getString("created_date"));
                category.setModifiedDate(rs.getString("modified_date"));
                category.setAdminId(rs.getLong("admin_id"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return categories;
    }
    
    public boolean update(CategoryBean category) {
        String sql = "UPDATE category SET "
                + "category_name = ?, "
                + "visibility = ?, "
                + "modified_date = ?, "
                + "admin_id = ? "
                + "WHERE id = ?";
        boolean flag = false;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, category.getCategoryName());
            preStmt.setByte(2, category.getVisibility());
            preStmt.setString(3, category.getModifiedDate());
            preStmt.setLong(4, category.getAdminId());
            preStmt.setLong(5, category.getId());
            flag =  preStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
    public boolean duplicateCategoryName(String categoryName) {
        return readByCategoryName(categoryName) != null;
    }
    
    public boolean duplicateCategoryName(String categoryName, long id) {
        String sql = "SELECT COUNT(*) "
                + "FROM category "
                + "WHERE category_name = ? "
                + "AND id != ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, categoryName);
            preStmt.setLong(2, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count > 0;
    }
    
    public long countAll() {
        String sql = "SELECT COUNT(*) "
                + "FROM category";
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
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
}