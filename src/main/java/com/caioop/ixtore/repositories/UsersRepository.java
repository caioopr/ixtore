package com.caioop.ixtore.repositories;


import com.caioop.ixtore.daos.UserDAO;
import com.caioop.ixtore.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UsersRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = " SELECT u.user_uuid as uuid, u.name, u.name, u.email, u.role FROM tb_users as u ")
    List<UserDAO> findAllUsers();
}
