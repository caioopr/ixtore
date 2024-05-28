package com.caioop.ixtore.repositories;

import com.caioop.ixtore.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity,Integer> {
    boolean existsByCode(String code);
}
