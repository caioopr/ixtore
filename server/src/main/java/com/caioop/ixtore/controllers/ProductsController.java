package com.caioop.ixtore.controllers;

import com.caioop.ixtore.dtos.ProductRegisterDTO;
import com.caioop.ixtore.dtos.UserRegisterDTO;
import com.caioop.ixtore.entities.ProductEntity;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    final private ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity register(@RequestBody ProductRegisterDTO product ){
        return productsService.register(product);
    }
}
