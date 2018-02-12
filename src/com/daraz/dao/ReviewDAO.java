package com.daraz.dao;

import com.daraz.bean.ReviewBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewDAO extends GenericDAO {
    
    public long create(ReviewBean review) {
        String sql = "INSERT INTO review ("
                + "message, rating, created_date, modified_date, buyer_id, product_id"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, review.getMessage());
            preStmt.setDouble(2, review.getRating());
            preStmt.setString(3, review.getCreatedDate());
            preStmt.setString(4, review.getModifiedDate());
            preStmt.setLong(5, review.getBuyerId());
            preStmt.setLong(6, review.getProductId());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public boolean canReview(long productId, long buyerId) {
        String sql = "SELECT status "
                + "FROM buyer_order bo, order_detail od, buyer b, product p "
                + "WHERE bo.id = od.buyer_order_id "
                + "AND bo.buyer_id = b.id "
                + "AND od.product_id = p.id "
                + "AND b.id = ? "
                + "AND p.id = ? "
                + "LIMIT 1";
        boolean result = false;
        byte status = 0;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, buyerId);
            preStmt.setLong(2, productId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                status = rs.getByte("status");
            }
            result = (status == (byte) 4);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return result;
    }
    
}