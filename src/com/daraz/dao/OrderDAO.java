package com.daraz.dao;

import com.daraz.bean.OrderBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends GenericDAO {
    
    public ArrayList<OrderBean> readByBuyerIdWithParameters(long buyerId, long buyerOrderId, long manufacturerId, long categoryId, byte status) {
        String sql = "SELECT * "
                + "FROM buyer_order bo, order_detail od, product p, manufacturer m, category c "
                + "WHERE bo.id = od.buyer_order_id "
                + "AND od.product_id = p.id "
                + "AND p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND bo.buyer_id = ? ";
        if (buyerOrderId > 0) {
            sql += "AND bo.id = ? ";
        }
        if (manufacturerId > 0) {
            sql += "AND m.id = ? ";
        }
        if (categoryId > 0) {
            sql += "AND c.id = ? ";
        }
        if (status > 0) {
            sql += "AND od.status = ? ";
        }
        ArrayList<OrderBean> orders = new ArrayList<>();
        OrderBean order = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            preStmt.setLong(1, buyerId);
            if (buyerOrderId > 0) {
                preStmt.setLong(2, buyerOrderId);
            }
            if (manufacturerId > 0) {
                if (buyerOrderId == 0) {
                    preStmt.setLong(2, manufacturerId);
                } else {
                    preStmt.setLong(3, manufacturerId);
                }
            }
            if (categoryId > 0) {
                if (buyerOrderId == 0 && manufacturerId == 0) {
                    preStmt.setLong(2, categoryId);
                } else if ((buyerOrderId > 0 && manufacturerId == 0) || (buyerOrderId == 0 && manufacturerId > 0)) {
                    preStmt.setLong(3, categoryId);
                } else {
                    preStmt.setLong(4, categoryId);
                }
            }
            if (status > 0) {
                if (buyerOrderId == 0 && manufacturerId == 0 && categoryId == 0) {
                    preStmt.setByte(2, status);
                } else if ((buyerOrderId > 0 && manufacturerId == 0 && categoryId == 0) || (buyerOrderId == 0 && manufacturerId > 0 && categoryId == 0) || (buyerOrderId == 0 && manufacturerId == 0 && categoryId > 0)) {
                    preStmt.setByte(3, status);
                } else if ((buyerOrderId > 0 && manufacturerId > 0 && categoryId == 0) || (buyerOrderId > 0 && manufacturerId == 0 && categoryId > 0) || (buyerOrderId == 0 && manufacturerId > 0 && categoryId > 0)) {
                    preStmt.setByte(4, status);
                } else {
                    preStmt.setByte(5, status);
                }
            }
            rs = preStmt.executeQuery();
            while (rs.next()) {
                order = new OrderBean();
                order.setBuyerOrderId(rs.getLong("bo.id"));
                order.setOrderDate(rs.getString("bo.created_date"));
                order.setProductName(rs.getString("p.product_name"));
                order.setOrderQuantity(rs.getInt("od.quantity"));
                order.setManufacturerName(rs.getString("m.manufacturer_name"));
                order.setCategoryName(rs.getString("c.category_name"));
                order.setStatus(rs.getByte("od.status"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return orders;
    }
    
    public ArrayList<OrderBean> readWithParameters(long buyerOrderId, long manufacturerId, long categoryId, byte status) {
        String sql = "SELECT * "
                + "FROM buyer_order bo, order_detail od, product p, manufacturer m, category c, user u, buyer b, address a "
                + "WHERE bo.buyer_id = b.id "
                + "AND bo.id = od.buyer_order_id "
                + "AND od.product_id = p.id "
                + "AND p.manufacturer_id = m.id "
                + "AND p.category_id = c.id "
                + "AND u.role_id = b.id "
                + "AND u.role = 2 "
                + "AND u.address_id = a.id ";
        if (buyerOrderId > 0) {
            sql += "AND bo.id = ? ";
        }
        if (manufacturerId > 0) {
            sql += "AND m.id = ? ";
        }
        if (categoryId > 0) {
            sql += "AND c.id = ? ";
        }
        if (status > 0) {
            sql += "AND od.status = ? ";
        }
        ArrayList<OrderBean> orders = new ArrayList<>();
        OrderBean order = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = connection.prepareStatement(sql);
            if (buyerOrderId > 0) {
                preStmt.setLong(1, buyerOrderId);
            }
            if (manufacturerId > 0) {
                if (buyerOrderId == 0) {
                    preStmt.setLong(1, manufacturerId);
                } else {
                    preStmt.setLong(2, manufacturerId);
                }
            }
            if (categoryId > 0) {
                if (buyerOrderId == 0 && manufacturerId == 0) {
                    preStmt.setLong(1, categoryId);
                } else if ((buyerOrderId > 0 && manufacturerId == 0) || (buyerOrderId == 0 && manufacturerId > 0)) {
                    preStmt.setLong(2, categoryId);
                } else {
                    preStmt.setLong(3, categoryId);
                }
            }
            if (status > 0) {
                if (buyerOrderId == 0 && manufacturerId == 0 && categoryId == 0) {
                    preStmt.setByte(1, status);
                } else if ((buyerOrderId > 0 && manufacturerId == 0 && categoryId == 0) || (buyerOrderId == 0 && manufacturerId > 0 && categoryId == 0) || (buyerOrderId == 0 && manufacturerId == 0 && categoryId > 0)) {
                    preStmt.setByte(2, status);
                } else if ((buyerOrderId > 0 && manufacturerId > 0 && categoryId == 0) || (buyerOrderId > 0 && manufacturerId == 0 && categoryId > 0) || (buyerOrderId == 0 && manufacturerId > 0 && categoryId > 0)) {
                    preStmt.setByte(3, status);
                } else {
                    preStmt.setByte(4, status);
                }
            }
            rs = preStmt.executeQuery();
            while (rs.next()) {
                order = new OrderBean();
                order.setBuyerOrderId(rs.getLong("bo.id"));
                order.setOrderDate(rs.getString("bo.created_date"));
                order.setProductName(rs.getString("p.product_name"));
                order.setOrderQuantity(rs.getInt("od.quantity"));
                order.setProductQuantity(rs.getInt("p.quantity"));
                order.setBuyerName(rs.getString("b.first_name") + " " + rs.getString("b.last_name"));
                order.setBuyerEmail(rs.getString("b.email"));
                order.setBuyerContactNo(rs.getString("b.contact_no"));
                order.setBuyerAddress(rs.getString("a.street") + " " + rs.getString("a.city") + " " + rs.getString("a.state") + " " + rs.getString("a.country") + " " + rs.getString("a.zip"));
                order.setManufacturerName(rs.getString("m.manufacturer_name"));
                order.setCategoryName(rs.getString("c.category_name"));
                order.setOrderDetailId(rs.getLong("od.id"));
                order.setStatus(rs.getByte("od.status"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(preStmt, rs);
        }
        return orders;
    }
    
}