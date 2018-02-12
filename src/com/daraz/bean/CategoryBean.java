package com.daraz.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable {
    
    private long id;
    private String categoryName;
    private byte visibility;
    private String createdDate;
    private String modifiedDate;
    private long adminId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte getVisibility() {
        return visibility;
    }

    public void setVisibility(byte visibility) {
        this.visibility = visibility;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
    
}