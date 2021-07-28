package com.learning.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private int productId;
    private String productDesc;
    private int cost;
    private int weight;
    private int numb;
    private Date createdTime;
    private Date updatedTime;
    private String supplierName;
    private String quantity;
    public void createdEntityHandle(){
        createdTime = new Date();
        updatedTime = new Date();
    }

    public void updatedEntityHandle(){
        updatedTime = new Date();
    }
}
