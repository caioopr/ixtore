package com.caioop.ixtore.services;

import com.caioop.ixtore.dtos.UserDTO;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    final private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity create(UserDTO userDTO){

        if(usersRepository.existsByEmail(userDTO.email())){
            throw new RuntimeException("User already exits");
        }

        UserEntity user = new UserEntity(userDTO);
        return usersRepository.save(user);
    }
}
