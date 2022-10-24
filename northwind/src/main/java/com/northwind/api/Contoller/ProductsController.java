package com.northwind.api.Contoller;


import com.northwind.business.abstracts.ProductService;
import com.northwind.core.result.Result;
import com.northwind.core.result.SuccessResult;
import com.northwind.core.utilities.DataResult;
import com.northwind.entities.DTO.ProductWithCategoryDTO;
import com.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public DataResult<List<Product>> getAll(){
        return this.productService.getAll();
    }

    @GetMapping("/getProductWithCategoryDeatils")
    public DataResult<List<ProductWithCategoryDTO>> getProductWithCategoryDeatils(){
        return this.productService.getProductWithCategoryDTO()  ;
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<Product>> getAllSorted(){
        return this.productService.getAllSorted();
    }

    @GetMapping("/getAllByPage")
    public DataResult<List<Product>> getAll(int pageNumber,int pageSize){
        return this.productService.getAll(pageNumber,pageSize);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Product product){
        this.productService.add(product);
        return new SuccessResult("Veri eklendi");
    }

    @GetMapping("/getByProductName")
    public DataResult<Product> getByProductName(@RequestParam String productName){
        return this.productService.getByProductName(productName);
    }

    @GetMapping("/getByProductNameAndCategoryId")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName,@RequestParam("categoryId") int categoryid){
        return this.productService.getByProductNameAndCategory(productName,categoryid);
    }

    @GetMapping("/getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName) {
        return this.productService.getByProductNameContains(productName);
    }

    @GetMapping("/getByProductNameOrCategory")
    public DataResult<List<Product>> getByProductNameOrCategory(@RequestParam("productName") String productName,@RequestParam("categoryId") int categoryId){
        return this.productService.getByProductNameOrCategory(productName,categoryId);
    }

    @GetMapping("/getByCategoryIn")
    public DataResult<List<Product>> getByCategoryIn(List<Integer> categories){
        return this.productService.getByCategoryIn(categories);
    }

    @GetMapping("/getByProductNameStartsWith")
    public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName){
        return this.productService.getByProductNameStartsWith(productName);
    }

    public DataResult<List<Product>> getByNameAndCategory(@RequestParam("productName") String productName,@RequestParam("categoryId") int categoryId){
        return this.productService.getByNameAndCategory(productName,categoryId);
    }



}
