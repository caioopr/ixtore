package com.caioop.ixtore.repositories;


import com.caioop.ixtore.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UsersRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
}
