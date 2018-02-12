package com.daraz.dao;

import com.daraz.bean.ProductBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends GenericDAO {
    
    public long create(ProductBean product) {
        String sql = "INSERT INTO product ("
                + "product_name, description, price, quantity, visibility, image_url, created_date, modified_date, admin_id, category_id, manufacturer_id"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, product.getProductName());
            preStmt.setString(2, product.getDescription());
            preStmt.setBigDecimal(3, product.getPrice());
            preStmt.setInt(4, product.getQuantity());
            preStmt.setByte(5, product.getVisibility());
            preStmt.setString(6, product.getImageURL());
            preStmt.setString(7, product.getCreatedDate());
            preStmt.setString(8, product.getModifiedDate());
            preStmt.setLong(9, product.getAdminId());
            preStmt.setLong(10, product.getCategoryId());
            preStmt.setLong(11, product.getManufacturerId());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public ArrayList<ProductBean> readAll() {
        String sql = "SELECT * FROM product";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVisibility(rs.getByte("visibility"));
                product.setImageURL(rs.getString("image_url"));
                product.setCreatedDate(rs.getString("created_date"));
                product.setModifiedDate(rs.getString("modified_date"));
                product.setAdminId(rs.getLong("admin_id"));
                product.setCategoryId(rs.getLong("category_id"));
                product.setManufacturerId(rs.getLong("manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readAllWithVisibility(byte visibility) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? ";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimited(int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setInt(1, limit);
            preStmt.setInt(2, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVisibility(rs.getByte("visibility"));
                product.setImageURL(rs.getString("image_url"));
                product.setCreatedDate(rs.getString("created_date"));
                product.setModifiedDate(rs.getString("modified_date"));
                product.setAdminId(rs.getLong("admin_id"));
                product.setCategoryId(rs.getLong("category_id"));
                product.setManufacturerId(rs.getLong("manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedWithVisibility(byte visibility, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setInt(4, limit);
            preStmt.setInt(5, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByManufacturer(long manufacturerId, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p "
                + "WHERE p.manufacturer_id = ? "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, manufacturerId);
            preStmt.setInt(2, limit);
            preStmt.setInt(3, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByManufacturerWithVisibility(byte visibility, long manufacturerId, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND m.id = ? "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, manufacturerId);
            preStmt.setInt(5, limit);
            preStmt.setInt(6, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByCategory(long categoryId, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p "
                + "WHERE p.category_id = ? "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, categoryId);
            preStmt.setInt(2, limit);
            preStmt.setInt(3, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByCategoryWithVisibility(byte visibility, long categoryId, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND c.id = ? "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, categoryId);
            preStmt.setInt(5, limit);
            preStmt.setInt(6, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByManufacturerAndQuery(long manufacturerId, String query, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p "
                + "WHERE p.manufacturer_id = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?) "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, manufacturerId);
            preStmt.setString(2, "%" + query + "%");
            preStmt.setString(3, "%" + query + "%");
            preStmt.setInt(4, limit);
            preStmt.setInt(5, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByManufacturerAndQueryWithVisibility(byte visibility, long manufacturerId, String query, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND m.id = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?) "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, manufacturerId);
            preStmt.setString(5, "%" + query + "%");
            preStmt.setString(6, "%" + query + "%");
            preStmt.setInt(7, limit);
            preStmt.setInt(8, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByCategoryAndQuery(long categoryId, String query, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p "
                + "WHERE p.category_id = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?) "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, categoryId);
            preStmt.setString(2, "%" + query + "%");
            preStmt.setString(3, "%" + query + "%");
            preStmt.setInt(4, limit);
            preStmt.setInt(5, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByCategoryAndQueryWithVisibility(byte visibility, long categoryId, String query, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND c.id = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?) "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, categoryId);
            preStmt.setString(5, "%" + query + "%");
            preStmt.setString(6, "%" + query + "%");
            preStmt.setInt(7, limit);
            preStmt.setInt(8, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByQuery(String query, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product "
                + "WHERE (product_name LIKE ? OR description LIKE ?) "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, "%" + query + "%");
            preStmt.setString(2, "%" + query + "%");
            preStmt.setInt(3, limit);
            preStmt.setInt(4, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVisibility(rs.getByte("visibility"));
                product.setImageURL(rs.getString("image_url"));
                product.setCreatedDate(rs.getString("created_date"));
                product.setModifiedDate(rs.getString("modified_date"));
                product.setAdminId(rs.getLong("admin_id"));
                product.setCategoryId(rs.getLong("category_id"));
                product.setManufacturerId(rs.getLong("manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ArrayList<ProductBean> readLimitedByQueryWithVisibility(byte visibility, String query, int limit, int offset) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?) "
                + "LIMIT ? OFFSET ?";
        ArrayList<ProductBean> products = new ArrayList<>();
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setString(4, "%" + query + "%");
            preStmt.setString(5, "%" + query + "%");
            preStmt.setInt(6, limit);
            preStmt.setInt(7, offset);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return products;
    }
    
    public ProductBean readById(long id) {
        String sql = "SELECT * "
                + "FROM product "
                + "WHERE id = ?";
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVisibility(rs.getByte("visibility"));
                product.setImageURL(rs.getString("image_url"));
                product.setCreatedDate(rs.getString("created_date"));
                product.setModifiedDate(rs.getString("modified_date"));
                product.setAdminId(rs.getLong("admin_id"));
                product.setCategoryId(rs.getLong("category_id"));
                product.setManufacturerId(rs.getLong("manufacturer_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return product;
    }
    
    public ProductBean readByIdWithVisibility(byte visibility, long id) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND p.id = ?";
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return product;
    }
    
    public ProductBean readByManufacturerWithVisibility(byte visibility, long manufacturerId) {
        String sql = "SELECT * "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND m.id = ?";
        ProductBean product = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, manufacturerId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
                product.setId(rs.getLong("p.id"));
                product.setProductName(rs.getString("p.product_name"));
                product.setDescription(rs.getString("p.description"));
                product.setPrice(rs.getBigDecimal("p.price"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setVisibility(rs.getByte("p.visibility"));
                product.setImageURL(rs.getString("p.image_url"));
                product.setCreatedDate(rs.getString("p.created_date"));
                product.setModifiedDate(rs.getString("p.modified_date"));
                product.setAdminId(rs.getLong("p.admin_id"));
                product.setCategoryId(rs.getLong("p.category_id"));
                product.setManufacturerId(rs.getLong("p.manufacturer_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return product;
    }
    
    public boolean update(ProductBean product) {
        String sql = "UPDATE product SET "
                + "product_name = ?, "
                + "description = ?, "
                + "price = ?, "
                + "quantity = ?, "
                + "visibility = ?, "
                + "image_url = ?, "
                + "modified_date = ?, "
                + "admin_id = ?, "
                + "category_id = ?, "
                + "manufacturer_id = ? "
                + "WHERE id = ?";
        boolean flag = false;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, product.getProductName());
            preStmt.setString(2, product.getDescription());
            preStmt.setBigDecimal(3, product.getPrice());
            preStmt.setInt(4, product.getQuantity());
            preStmt.setByte(5, product.getVisibility());
            preStmt.setString(6, product.getImageURL());
            preStmt.setString(7, product.getModifiedDate());
            preStmt.setLong(8, product.getAdminId());
            preStmt.setLong(9, product.getCategoryId());
            preStmt.setLong(10, product.getManufacturerId());
            preStmt.setLong(11, product.getId());
            flag =  preStmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
    public long countAll() {
        String sql = "SELECT COUNT(*) "
                + "FROM product";
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
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countWithVisibility(byte visibility) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByManufacturer(long manufacturerId) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p "
                + "WHERE p.manufacturer_id = ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, manufacturerId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByManufacturerWithVisibility(byte visibility, long manufacturerId) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND m.id = ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, manufacturerId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByCategory(long categoryId) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p "
                + "WHERE p.category_id = ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, categoryId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByCategoryWithVisibility(byte visibility, long categoryId) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND c.id = ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, categoryId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countAllByQuery(String query) {
        String sql = "SELECT COUNT(*) "
                + "FROM product "
                + "WHERE (product_name LIKE ? OR description LIKE ?)";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, "%" + query + "%");
            preStmt.setString(2, "%" + query + "%");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByQueryWithVisibility(byte visibility, String query) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?)";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setString(4, "%" + query + "%");
            preStmt.setString(5, "%" + query + "%");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByManufacturerAndQuery(long manufacturerId, String query) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p "
                + "WHERE p.manufacturer_id = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?)";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, manufacturerId);
            preStmt.setString(2, "%" + query + "%");
            preStmt.setString(3, "%" + query + "%");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByManufacturerAndQueryWithVisibility(byte visibility, long manufacturerId, String query) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND m.id = ? "
                + "AND (product_name LIKE ? OR description LIKE ?)";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, manufacturerId);
            preStmt.setString(5, "%" + query + "%");
            preStmt.setString(6, "%" + query + "%");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByCategoryAndQuery(long categoryId, String query) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p "
                + "WHERE p.category_id = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?)";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, categoryId);
            preStmt.setString(2, "%" + query + "%");
            preStmt.setString(3, "%" + query + "%");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
    public long countByCategoryAndQueryWithVisibility(byte visibility, long categoryId, String query) {
        String sql = "SELECT COUNT(*) "
                + "FROM product p, manufacturer m, category c "
                + "WHERE p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND m.visibility = ? "
                + "AND c.visibility = ? "
                + "AND p.visibility = ? "
                + "AND c.id = ? "
                + "AND (p.product_name LIKE ? OR p.description LIKE ?)";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, visibility);
            preStmt.setByte(2, visibility);
            preStmt.setByte(3, visibility);
            preStmt.setLong(4, categoryId);
            preStmt.setString(5, "%" + query + "%");
            preStmt.setString(6, "%" + query + "%");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return count;
    }
    
}