package com.learning.mybatis.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learning.mybatis.controller.response.ProductResponse;
import com.learning.mybatis.entity.Brand;
import com.learning.mybatis.entity.Product;
import com.learning.mybatis.entity.Supplier;
import com.learning.mybatis.mapper.ProductMapper;
import com.learning.mybatis.service.ProductService;
import com.learning.mybatis.util.SpringContextUtil;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;



@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper productMapper;

//    @Override
//    public Product findProduct(int id) {
//        return productMapper.getProduct(id);
//    }

    @Override
    public Product findProduct(int id) {
        return productMapper.getProduct(id);
    }

    @Override
    public void updateProductById(int id) {
        try {
            productMapper.updateProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAllProduct(){
        return productMapper.getAllProduct();
    }



    @Override
    public List<Product> findProductBySupplier(int supplierId){
        return productMapper.getProductBySupplierId(supplierId);
    }

//    @Override
//    public int insertProduct(Product product){
//        return productMapper.insertProduct(product);
//    }

    @Override
    public int insertProduct(Product product){
        return productMapper.insertProduct(product);
    }

    @Override
    public void deleteProductByDesc(String desc){
        productMapper.deleteProductByName(desc);
    }

    @Override
    public int updateNormalProduct(String desc, int numb){
        List<Product> productList = productMapper.getProductByDesc(desc);
        try {
            for (Product product : productList) {
                product.setNumb(numb);
                productMapper.updateProduct(product);
            }
            return 0;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void update(Product product){
           productMapper.updateProduct(product);
    }

    @Override
    public int updateBatchProductNumByDesc(String desc, int numb, int weight){
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextUtil.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = null;
        List<Product> list = productMapper.getProductByDesc(desc);
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            int batchCount = 5; // Submit quantity, and submit as soon as it reaches that quantity

            for (int index = 0; index < list.size(); index++) {
                Product product = list.get(index);
                product.setWeight(weight);
                product.setNumb(numb);
                mapper.updateProduct(product);

                if(index != 0 && index%batchCount == 0){
                    sqlSession.commit();
                }
            }
              sqlSession.commit();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
//            sqlSession.rollback();
            return -2;
        }
        finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

    public List<Product> getProductAndSupplierName(int supplierId){
        return productMapper.getProductAndSupplierName(supplierId);
    }

    public List<Supplier> getAllSupplier(){
        return productMapper.getAllSupplier();
    }

    public void insertProductWithWrongAnnotation(Product product){
         productMapper.insertProductWithWrongAnnotation(product);
    }

    public ProductResponse findByPage(int offset, int limit){
        ProductResponse productResponse = new ProductResponse();
        PageHelper.startPage(offset, limit);
        Page<Product> productPage = new Page<>();
        productPage = productMapper.findByPage();
        productResponse.setData(productPage);
        productResponse.setTotal(productPage.getTotal());
        return productResponse;
    }

    public List<Brand> findAllBrand(){
        return productMapper.findAllBrand();
    }


}
