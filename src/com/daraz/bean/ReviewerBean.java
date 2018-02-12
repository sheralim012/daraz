package com.daraz.bean;

import java.io.Serializable;

public class ReviewerBean implements Serializable {
    
    private String message;
    private String buyerName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    
}