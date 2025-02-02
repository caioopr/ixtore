package com.caioop.ixtore.services;

import com.caioop.ixtore.dtos.ProductRegisterDTO;
import com.caioop.ixtore.dtos.ProductUpdateDTO;
import com.caioop.ixtore.entities.ProductEntity;
import com.caioop.ixtore.exceptions.DuplicatedRegistrationException;
import com.caioop.ixtore.exceptions.ProductNotFoundException;
import com.caioop.ixtore.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// TODO :
//  verify if the user id exists
//  change return type
//  runtime exceptions
//  pagination for lists

@Service
public class ProductsService {

    @Autowired
    final private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public ProductEntity register(ProductRegisterDTO productDTO,UUID userId) throws DuplicatedRegistrationException {
        if (productsRepository.existsByCode(productDTO.code())) {
            throw new DuplicatedRegistrationException("Product already exist.");
        }

        //TODO: make the fk_user_uuid be defined by the id stored in the token
        ProductEntity product = new ProductEntity(productDTO);
        product.setFk_user_uuid(userId);
        return productsRepository.save(product);


    }

    public List<ProductEntity> getProductsByUserId(UUID userId) {
        return productsRepository.findProductsByUserUUID(userId);
    }


    public ProductEntity getByCode(String code, UUID userId) throws ProductNotFoundException {
        // TODO: verify the user id requesting the product
        ProductEntity product = productsRepository
                .findByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        if(!product.getFk_user_uuid().equals(userId)) {
            throw new ProductNotFoundException("User's product not found.");
        }

        return product;
    }

    // TODO: update return type and deletion
    public ProductEntity update(String productCode, ProductUpdateDTO updatedProduct, UUID userId) throws ProductNotFoundException {
        ProductEntity product = productsRepository
                .findByCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        if(!product.getFk_user_uuid().equals(userId)) {
            throw new ProductNotFoundException("User's product not found.");
        }

        // TODO: ProductUpdateDTO to Product Entity "Constructor"
        product.setName(updatedProduct.name());
        product.setPrice(updatedProduct.price());
        product.setDescription(updatedProduct.description());
        product.setWeight(updatedProduct.weight());
        product.setSupplier(updatedProduct.supplier());

        productsRepository.save(product);
        return product;
    }

    public void delete(String productCode, UUID userId) throws ProductNotFoundException {

        //TODO: check if the code exists, then delete where the code is equal
        ProductEntity product = productsRepository
                .findByCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        if(!product.getFk_user_uuid().equals(userId)) {
            throw new ProductNotFoundException("User's product not found.");
        }

        productsRepository.delete(product);
    }
}
