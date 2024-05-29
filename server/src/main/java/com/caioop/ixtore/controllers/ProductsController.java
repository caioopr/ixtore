package com.caioop.ixtore.controllers;

import com.caioop.ixtore.dtos.ProductRegisterDTO;
import com.caioop.ixtore.dtos.UserRegisterDTO;
import com.caioop.ixtore.entities.ProductEntity;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getProductsByUser(@RequestParam(value="user_id") String id){
        return productsService.getProductsByUserId(UUID.fromString(id));
    }

    @GetMapping("{code}")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity getProductsByCode(@PathVariable("code") String code){
        return productsService.getByCode(code);
    }
}
