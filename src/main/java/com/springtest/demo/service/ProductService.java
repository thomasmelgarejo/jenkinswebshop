package com.springtest.demo.service;

import com.springtest.demo.model.Product;
import com.springtest.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void create(Product product){
        productRepository.create(product);
    }

    public void delete(long id){
        productRepository.delete(id);
    }

    public void update(Product product, int id){
        productRepository.update(product, id);
    }

    public List<Product> readAll(){
        List<Product> products = new ArrayList<>();
        for (Product product:productRepository.readAll()) {
            products.add(product);
        }
        return products;
    }

    public Product read(long idProducts){
        return productRepository.read(idProducts);
    }

    public long getLastId(){
        return  productRepository.getLastId();
    }

}
