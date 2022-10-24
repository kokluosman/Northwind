package com.northwind.business.abstracts;

import com.northwind.core.result.Result;
import com.northwind.core.utilities.DataResult;
import com.northwind.entities.DTO.ProductWithCategoryDTO;
import com.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductService {

    DataResult<List<Product>> getAll();

    DataResult<List<Product>> getAllSorted();

    DataResult<List<Product>> getAll(int pageNumber, int pageSize);
    Result add(Product product);

    DataResult<Product> getByProductName(String productName);

    DataResult<Product> getByProductNameAndCategory(String productName,int categoryId);

    DataResult<List<Product>> getByProductNameOrCategory(String productName,int categoryId);

    DataResult<List<Product>> getByCategoryIn(List<Integer> categories);

    DataResult<List<Product>> getByProductNameContains(String productName);

    DataResult<List<Product>> getByProductNameStartsWith(String productName);

    DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId);

    DataResult<List<ProductWithCategoryDTO>> getProductWithCategoryDTO();
}
