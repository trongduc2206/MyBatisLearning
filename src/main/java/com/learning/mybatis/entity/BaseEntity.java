package com.learning.mybatis.entity;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class BaseEntity {
    private Date createdTime;
    private Date updatedTime;

    public void createdEntityHandle(){
        createdTime = new Date();
        updatedTime = new Date();
    }

    public void updatedEntityHandle(){
        updatedTime = new Date();
    }
}
