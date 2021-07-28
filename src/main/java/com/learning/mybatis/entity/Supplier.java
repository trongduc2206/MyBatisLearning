package com.learning.mybatis.entity;

import lombok.Data;

import java.util.List;

@Data
public class Supplier {
    private int supplierId;
    private String name;
    private String location;
    private String subquery;
    private List<Branch> branchList;
}
