package com.northwind.business.concretes;

import com.northwind.business.abstracts.ProductService;
import com.northwind.core.result.Result;
import com.northwind.core.result.SuccessResult;
import com.northwind.core.utilities.DataResult;
import com.northwind.core.utilities.SuccessDataResult;
import com.northwind.dataAccess.abstracts.ProductDao;
import com.northwind.entities.DTO.ProductWithCategoryDTO;
import com.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(this.productDao.findAll()
                ,"Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"productName");
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort)
                ,"Data Sıralandı.");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent()
                ,"Sayfa getirildi");
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Veri eklendi");
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDao.getByProductName(productName)
                ,"Data isme göre Listelendi");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategory(String productName, int categoryId) {
        return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId)
                ,"Data isim ve catogryId ye göre listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategory(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategory_CategoryId(productName,categoryId)
                ,"Data isim ve catogryId yada göre listelendi");
    }

    @Override
    public DataResult<List<Product>> getByCategoryIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByCategoryIn(categories)
                ,"Data parametreyi içeriyormu in");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName)
                , "Data isim ismi içeriyor mu contains");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName)
                ,"Data şunla başlıyor mu");
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByNameAndCategory(productName,categoryId)
                ,"@Queryi anotasyonu");
    }

    @Override
    public DataResult<List<ProductWithCategoryDTO>> getProductWithCategoryDTO() {
        return new SuccessDataResult<List<ProductWithCategoryDTO>>(this.productDao.getProductWithCategoryDetails()
                ,"Inner Join Sorgusu Çalıştırıldı");
    }

}
