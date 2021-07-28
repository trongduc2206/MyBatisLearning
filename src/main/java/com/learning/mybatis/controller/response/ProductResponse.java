package com.learning.mybatis.controller.response;

import com.learning.mybatis.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String status;
    private List<Product> data;
    private long total;
}
