package com.learning.mybatis.mapper;

import com.github.pagehelper.Page;
import com.learning.mybatis.entity.Branch;
import com.learning.mybatis.entity.Brand;
import com.learning.mybatis.entity.Product;
import com.learning.mybatis.entity.Supplier;
import com.learning.mybatis.typehandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper {


//    @Results(id = "brandResultMapper",value = {
//            @Result(property = "productDesc", column = "Product_desc")
//    })

    @Select("SELECT * FROM products WHERE ProductId = #{id}")
    @Results(id = "productResultMapper",value = {
            @Result(property = "productDesc", column = "Product_desc")
    })
    Product getProduct(@Param("id") int id);

    @Update("update products set Product_desc=#{productDesc}, Weight=#{weight}, Numb=#{numb}, updated_time=#{updatedTime} where ProductID=#{productId}")
    void updateProduct(Product product);

    @Insert("insert into products(Product_desc, Cost, Weight, Numb, created_time, updated_time) values( #{productDesc}, #{cost}, #{weight}, #{numb}, #{createdTime}, #{updatedTime})")
    @Options(useGeneratedKeys = true, keyProperty = "productId", keyColumn = "ProductID" )
    int insertProduct(Product product);

    @Update("UPDATE products SET Product_desc='Motor' , Numb=234 where ProductId = #{id}")
    void updateProductById(@Param("id") int id);

    @Delete("delete from products where Product_desc = #{desc}")
    void deleteProductByName(@Param("desc") String desc);

    @Select("CALL GetProductById(#{id})")
//    @ResultMap("productResultMapper")
    Product getProductByIdStoredProcedure(@Param("id") int id);

    @Select("select * from products where Product_desc=#{desc}")
    @ResultMap("productResultMapper")
    List<Product> getProductByDesc(@Param("desc") String desc);



    //Select join 3 tables to get products list by supplierId
    @Select("select products.* from products, supplier, supply " +
            "where products.ProductID = supply.ProductID and supplier.SupplierID = supply.SupplierID and supplier.SupplierId = #{supplierId}")
//    @ResultMap("productResultMapper")
    List<Product> getProductBySupplierId(@Param("supplierId") int supplierId);

    @Select("CALL GetAllProducts()")
//    @ResultMap("productResultMapper")
    List<Product> getAllProductStoredProcedure();





    @Insert("CALL CreateProduct(#{productDesc}, #{cost}, #{weight}, #{numb}, #{createdTime}, #{updatedTime})")
    int insertProductStoredProcedure(Product product);



    //One-to-many join select
//    @Select("select supplier.name, supplier.supplierID from supplier")
//    @Results(value = {
//            @Result(property = "name", column = "Name"),
////            @Result(property = "location", column = "Location"),
//
//            @Result(property = "branchList", column = "SupplierID",javaType = List.class, many = @Many(select = "getBranchBySupplierId"))
//    })
//    List<Supplier> getAllSupplier();

//    @Results(value = {
//            @Result(property = "name", column = "Name"),
////            @Result(property = "location", column = "Location"),
//
//            @Result(property = "branchList", column = "SupplierID",javaType = List.class, many = @Many(select = "getBranchBySupplierId"))
//    })

    @Select("select supplier.name as supplierName,products.ProductID,products.Product_desc, products.cost, supply.quantity from products, supply, supplier " +
            "where products.ProductID = supply.ProductID and supplier.SupplierID = supply.SupplierID and supplier.SupplierId = #{supplierId}")
//    @Results({
//            @Result(column = "name", property = "supplierName")
//    })
    List<Product> getProductAndSupplierName(@Param("supplierId") int supplierId);

    @Select("select * from supplier")
    @TypeDiscriminator(javaType = String.class, column = "sub_query", cases = {
            @Case(value = "name", type = Supplier.class, results = {
                    @Result(property = "name", column = "Name"),
                    @Result(property = "branchList", column = "SupplierID", many = @Many(select = "getBranchNameBySupplierId"))
            }),
            @Case(value="address", type = Supplier.class, results = {
                    @Result(property = "name", column = "Name"),
                    @Result(property = "branchList", column = "SupplierID", many = @Many(select = "getBranchAddressBySupplierId"))
            })
    })
    List<Supplier> getAllSupplier();

    @Select("select branch.name from branch where sid = #{supplierId}")
    List<Branch> getBranchNameBySupplierId(@Param("id") int supplierId);

    @Select("select branch.address from branch where sid = #{supplierId}")
    List<Branch> getBranchAddressBySupplierId(@Param("id") int supplierId);

    @Select("insert into products(Product_desc, Cost, Weight, Numb, created_time, updated_time) values( #{productDesc}, #{cost}, #{weight}, #{numb}, #{createdTime}, #{updatedTime}) ")
    void insertProductWithWrongAnnotation(Product product);

    @Select("select * from products")
    Page<Product> findByPage();

    @Select("select * from brand")
    @Results(id="uuidMapper", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
    })
    List<Brand> findAllBrand();

    @ResultMap("uuidMapper")
    List<Brand> findBrandById();

    //this function is mapped in ProductMapper.xml
    @Select("select * from products")
    @ResultMap("productResultMapper")
    List<Product> getAllProduct();
}
