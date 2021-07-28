package com.learning.mybatis.service;

import com.github.pagehelper.Page;
import com.learning.mybatis.controller.response.ProductResponse;
import com.learning.mybatis.entity.Branch;
import com.learning.mybatis.entity.Brand;
import com.learning.mybatis.entity.Product;
import com.learning.mybatis.entity.Supplier;

import java.util.List;

public interface ProductService {
    public Product findProduct(int id);
    public void updateProductById(int id);
    public void update(Product product);
    public List<Product> findAllProduct();
    public List<Product> findProductBySupplier(int supplierId);
    public int insertProduct(Product product);
    public int updateBatchProductNumByDesc(String desc, int weight, int numb);
    public int updateNormalProduct(String desc, int numb);
    public void deleteProductByDesc(String desc);

    public List<Supplier> getAllSupplier();

    public List<Product> getProductAndSupplierName(int supplierId);

    public void insertProductWithWrongAnnotation(Product product);

    public ProductResponse findByPage(int offset, int limit);

    public List<Brand> findAllBrand();

}
