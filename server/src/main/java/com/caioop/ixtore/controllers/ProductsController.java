package com.caioop.ixtore.controllers;

import com.caioop.ixtore.dtos.ProductRegisterDTO;
import com.caioop.ixtore.dtos.ProductUpdateDTO;
import com.caioop.ixtore.entities.ProductEntity;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.exceptions.DuplicatedRegistrationException;
import com.caioop.ixtore.exceptions.ProductNotFoundException;
import com.caioop.ixtore.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    public ProductEntity register(@RequestBody ProductRegisterDTO product,Authentication currentUser) throws DuplicatedRegistrationException {
        UserEntity currentUserPrincipal = (UserEntity) currentUser.getPrincipal();
        return productsService.register(product,currentUserPrincipal.getUser_uuid());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getProductsByUser(@RequestParam(value="user_id") String id){
        return productsService.getProductsByUserId(UUID.fromString(id));
    }

    @GetMapping("{code}")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity getProductsByCode(@PathVariable("code") String code, Authentication currentUser) throws ProductNotFoundException{
        UserEntity currentUserPrincipal = (UserEntity) currentUser.getPrincipal();

        return productsService.getByCode(code,currentUserPrincipal.getUser_uuid());
    }

    @PutMapping("{code}")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity updateProduct(@PathVariable("code") String code, @RequestBody ProductUpdateDTO product,Authentication currentUser) throws ProductNotFoundException {
        UserEntity currentUserPrincipal = (UserEntity) currentUser.getPrincipal();
        return productsService.update(code, product, currentUserPrincipal.getUser_uuid());
    }

    @DeleteMapping("{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("code") String code,Authentication currentUser) throws ProductNotFoundException{
        UserEntity currentUserPrincipal = (UserEntity) currentUser.getPrincipal();
        productsService.delete(code,currentUserPrincipal.getUser_uuid());
    }
}
