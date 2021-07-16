package com.example.springservice.service;

import com.example.springservice.entity.Product;
import com.example.springservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;


@Component(value = "productService")
@WebService
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @WebMethod
    public Product addProduct(Product p){
        return productRepository.save(p);
    }

    @WebMethod
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @WebMethod
    public Product sellProduct(long productId, int quantity) {
        Product existProduct = productRepository.findById(productId).orElse(null);
        if (existProduct != null && existProduct.getQuantity() > quantity){
            existProduct.setQuantity(existProduct.getQuantity() - quantity);
            productRepository.save(existProduct);
            return existProduct;
        }
        return null;
    }
}
