package com.caioop.ixtore.services;

import com.caioop.ixtore.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    final private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    public void register(){}
    public void getAll(){}
    public void get(){}
    public void update(){}
    public void delete(){}

}
