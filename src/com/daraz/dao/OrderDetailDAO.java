package com.daraz.dao;

import com.daraz.bean.OrderDetailBean;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailDAO extends GenericDAO {
    
    public long create(OrderDetailBean orderDetail) {
        String sql = "INSERT INTO order_detail ("
                + "quantity, status, product_id, buyer_order_id"
                + ") VALUES ("
                + "?, ?, ?, ?"
                + ")";
        long lastInsertId = 0;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setInt(1, orderDetail.getQuantity());
            preStmt.setByte(2, orderDetail.getStatus());
            preStmt.setLong(3, orderDetail.getProductId());
            preStmt.setLong(4, orderDetail.getBuyerOrderId());
            if (preStmt.executeUpdate() == 1) {
                lastInsertId = lastInsertId(preStmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return lastInsertId;
    }
    
    public boolean update(OrderDetailBean orderDetail) {
        String sql = "UPDATE order_detail SET "
                + "status = ? "
                + "WHERE id = ?";
        boolean flag = false;
        PreparedStatement preStmt = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setByte(1, orderDetail.getStatus());
            preStmt.setLong(2, orderDetail.getId());
            flag =  preStmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt);
        }
        return flag;
    }
    
}