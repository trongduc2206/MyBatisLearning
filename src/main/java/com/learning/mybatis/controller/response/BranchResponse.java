package com.learning.mybatis.controller.response;

import com.learning.mybatis.entity.Product;
import com.learning.mybatis.entity.Supplier;

import java.util.List;

public class BranchResponse {
    private String status = "success";
    private String name ;
    private String location;
    private Supplier supplier;
}
