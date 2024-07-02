package com.caioop.ixtore.services;

import com.caioop.ixtore.dtos.ProductRegisterDTO;
import com.caioop.ixtore.dtos.ProductUpdateDTO;
import com.caioop.ixtore.entities.ProductEntity;
import com.caioop.ixtore.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    public ProductEntity register(ProductRegisterDTO productDTO){
        if(productsRepository.existsByCode(productDTO.code())){
            throw new RuntimeException("Product already exist.");
        }

        ProductEntity product = new ProductEntity(productDTO);
        return productsRepository.save(product);


    }

    public List<ProductEntity> getProductsByUserId(UUID userId){
        return productsRepository.findProdutctsByUserUUID(userId);
    }
    public ProductEntity getByCode(String code){
        return productsRepository.findByCode(code);
    }
    // TODO: update return type and deletion
    public ProductEntity update(String productCode, ProductUpdateDTO updatedProduct){
        ProductEntity product = productsRepository.findByCode(productCode);

        if (product == null){
            throw new RuntimeException("Product not found.");
        }

        product.setName(updatedProduct.name());
        product.setPrice(updatedProduct.price());
        product.setDescription(updatedProduct.description());
        product.setWeight(updatedProduct.weight());
        product.setSupplier(updatedProduct.supplier());

        productsRepository.save(product);
        return product;
    }
    public void delete(String productCode){
        //TODO: check if the code exists, then delete where the code is equal
        ProductEntity product = productsRepository.findByCode(productCode);

        if (product == null){
            throw new RuntimeException("Product not found.");
        }

        productsRepository.delete(product);
        return;
    }

}
