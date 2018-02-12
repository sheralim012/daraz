package com.daraz.dao;

import com.daraz.bean.ReviewerBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewerDAO extends GenericDAO {
    
    public ArrayList<ReviewerBean> readByProductId(long productId) {
        String sql = "SELECT * "
                + "FROM review r, product p, buyer b "
                + "WHERE p.id = r.product_id "
                + "AND p.visibility = 1 "
                + "AND b.id = r.buyer_id "
                + "AND product_id = ?";
        ArrayList<ReviewerBean> reviewers = new ArrayList<>();
        ReviewerBean reviewer = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, productId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                reviewer = new ReviewerBean();
                reviewer.setMessage(rs.getString("r.message"));
                reviewer.setBuyerName(rs.getString("b.first_name") + " " + rs.getString("b.last_name"));
                reviewers.add(reviewer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return reviewers;
    }
    
    public double readRatingByProductId(long productId) {
        String sql = "SELECT ROUND(AVG(rating), 1) "
                + "FROM review r "
                + "WHERE product_id = ?";
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        double rating = 0.0;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, productId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                rating = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return rating;
    }
    
}