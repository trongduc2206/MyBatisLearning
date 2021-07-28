package com.learning.mybatis.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Brand {
    private UUID id;
    private String name;
    private int numb;
}
