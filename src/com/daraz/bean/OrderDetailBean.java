package com.daraz.bean;

import java.io.Serializable;

public class OrderDetailBean implements Serializable {
    
    private long id;
    private int quantity;
    private byte status;
    private long productId;
    private long buyerOrderId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(long buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }
    
}