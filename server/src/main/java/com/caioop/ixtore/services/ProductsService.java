package com.caioop.ixtore.services;

import com.caioop.ixtore.dtos.ProductRegisterDTO;
import com.caioop.ixtore.entities.ProductEntity;
import com.caioop.ixtore.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsService {

    @Autowired
    final private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    // TODO : verify if the user_uuid exists and change return type
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
    public void update(){}
    public void delete(){}

}
