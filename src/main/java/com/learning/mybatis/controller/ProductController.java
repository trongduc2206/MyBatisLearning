package com.learning.mybatis.controller;

import com.learning.mybatis.controller.response.ProductResponse;
import com.learning.mybatis.entity.Brand;
import com.learning.mybatis.entity.Product;
import com.learning.mybatis.entity.Supplier;
import com.learning.mybatis.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/internal/products/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/single")
    public ProductResponse getProduct(@RequestParam int id){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus("success");
        List<Product> productList = new ArrayList<>();
        productList.add(productService.findProduct(id));
        productResponse.setData(productList);
        return productResponse;
    }

    @GetMapping("/all")
    public ProductResponse getAllProduct(){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus("success");
        List<Product> productList = productService.findAllProduct();
        productResponse.setData(productList);
        return productResponse;
    }
    @GetMapping("/updateById")
    public ProductResponse updateProduct(@RequestParam int id){
        ProductResponse  productResponse = new ProductResponse();
        productResponse.setStatus("success");
        List<Product> productList = new ArrayList<>();
        productService.updateProductById(id);
        productList.add(productService.findProduct(id));
        productResponse.setData(productList);
        return productResponse;
    }

    @GetMapping("/find-by-supplier")
    public ProductResponse getProductBySupplier(@RequestParam int supplierId){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus("success");
        List<Product> productList = productService.findProductBySupplier(supplierId);
        productResponse.setData(productList);
        return productResponse;
    }

    @GetMapping("/update-batch")
    public ProductResponse updateBatch(@RequestParam String desc, @RequestParam int weight, @RequestParam int numb){
        ProductResponse productResponse = new ProductResponse();
//        Product product = new Product();
//        product.setProductDesc(desc);
//        product.setNumb(150);
//        product.setWeight(100);
        int rs = productService.updateBatchProductNumByDesc(desc, weight, numb);
        if(rs==0){
            productResponse.setStatus("success");
            return productResponse;
        } else {
            productResponse.setStatus("fail");
            return productResponse;
        }
    }

    @GetMapping("/update-simple")
    public ProductResponse updateSimple(@RequestParam String desc, @RequestParam int numb){
        ProductResponse productResponse = new ProductResponse();
        int rs = productService.updateNormalProduct(desc, numb);
        if(rs == 0) {
            productResponse.setStatus("success");
            return productResponse;
        }
        productResponse.setStatus("Fail");
        return productResponse;
    }

    @PutMapping
    public ProductResponse update(@RequestBody Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus("success");
        productService.update(product);
        Product productUpdated = productService.findProduct(product.getProductId());
        List<Product> productList = new ArrayList<>();
        productList.add(productUpdated);
        productResponse.setData(productList);
        return productResponse;
    }

    @PostMapping("/insert")
    public ProductResponse insertProduct(@RequestBody Product product){
        ProductResponse productResponse = new ProductResponse();
        productService.insertProduct(product);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productResponse.setStatus("success");
        productResponse.setData(productList);
        return productResponse;
    }

    @GetMapping("/delete")
    public ProductResponse deleteProduct(@RequestParam String desc){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus("success");
        productService.deleteProductByDesc(desc);
        productResponse.setData(productService.findAllProduct());
        return productResponse;
    }

    @GetMapping("/products-supplier-name")
    public ProductResponse getProductAndSupplierName(@RequestParam int supplierId){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus("success");
        productResponse.setData(productService.getProductAndSupplierName(supplierId));
        return productResponse;
    }


    @GetMapping("/all-supplier")
    public List<Supplier> getAllSupplier(){
        return productService.getAllSupplier();
    }

    @PostMapping("/insert-test")
    public ProductResponse insertProductTest(@RequestBody Product product){
        ProductResponse productResponse = new ProductResponse();
             productService.insertProductWithWrongAnnotation(product);
        productResponse.setStatus("success");
        productResponse.setData(productService.findAllProduct());
        return productResponse;
    }

    @GetMapping("find-by-page")
    public ProductResponse findByPage(@RequestParam int offset, @RequestParam int limit){
        return productService.findByPage(offset, limit);
    }

    @GetMapping("/find-all-brand")
    public List<Brand> findAllBrand(){
        return productService.findAllBrand();
    }


}
