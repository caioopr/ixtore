package com.caioop.ixtore.services;

import com.caioop.ixtore.dtos.UserDTO;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    final private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity create(UserDTO userDTO){
        UserEntity user = new UserEntity();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.role());


        return usersRepository.save(user);
    }
}
