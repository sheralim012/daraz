package com.daraz.dao;

import com.daraz.bean.BuyerOrderBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyerOrderDAO extends GenericDAO {
    
    public long create(BuyerOrderBean buyerOrder) {
        String sql = "INSERT INTO buyer_order ("
                + "created_date, buyer_id"
                + ") VALUES ("
                + "?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, buyerOrder.getCreatedDate());
            preStmt.setLong(2, buyerOrder.getBuyerId());
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
    
    public ArrayList<BuyerOrderBean> readByBuyerId(long buyerId) {
        String sql = "SELECT DISTINCT(id), created_date, buyer_id "
                + "FROM buyer_order "
                + "WHERE buyer_id = ?";
        ArrayList<BuyerOrderBean> buyerOrders = new ArrayList<>();
        BuyerOrderBean buyerOrder = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, buyerId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                buyerOrder = new BuyerOrderBean();
                buyerOrder.setId(rs.getLong("id"));
                buyerOrder.setCreatedDate(rs.getString("created_date"));
                buyerOrder.setBuyerId(rs.getLong("buyer_id"));
                buyerOrders.add(buyerOrder);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuyerOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return buyerOrders;
    }
    
}