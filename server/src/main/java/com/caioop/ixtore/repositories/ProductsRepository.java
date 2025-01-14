package com.caioop.ixtore.repositories;

import com.caioop.ixtore.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductsRepository extends JpaRepository<ProductEntity,Integer> {
    boolean existsByCode(String code);

    Optional<ProductEntity> findByCode(String code);

    @Query(nativeQuery = true, value = " SELECT * FROM tb_products as p WHERE p.fk_user_uuid =:userId ")
    List<ProductEntity> findProductsByUserUUID(UUID userId);
}
