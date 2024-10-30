package com.caioop.ixtore.controllers;

import com.caioop.ixtore.dtos.ProductRegisterDTO;
import com.caioop.ixtore.dtos.ProductUpdateDTO;
import com.caioop.ixtore.entities.ProductEntity;
import com.caioop.ixtore.exceptions.DuplicatedRegistrationException;
import com.caioop.ixtore.exceptions.ProductNotFoundException;
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
    public ProductEntity register(@RequestBody ProductRegisterDTO product ) throws DuplicatedRegistrationException {
        return productsService.register(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getProductsByUser(@RequestParam(value="user_id") String id){
        return productsService.getProductsByUserId(UUID.fromString(id));
    }

    @GetMapping("{code}")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity getProductsByCode(@PathVariable("code") String code) throws ProductNotFoundException{
        return productsService.getByCode(code);
    }

    @PutMapping("{code}")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity updateProduct(@PathVariable("code") String code, @RequestBody ProductUpdateDTO product) throws ProductNotFoundException {
        return productsService.update(code, product);
    }

    @DeleteMapping("{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("code") String code) throws ProductNotFoundException{
        productsService.delete(code);
    }
}
