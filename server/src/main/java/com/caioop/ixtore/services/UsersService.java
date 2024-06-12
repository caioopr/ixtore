package com.caioop.ixtore.services;

import com.caioop.ixtore.dtos.UserDTO;
import com.caioop.ixtore.dtos.UserRegisterDTO;
import com.caioop.ixtore.dtos.UserUpdateDTO;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.entities.UserRole;
import com.caioop.ixtore.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UsersService {

    @Autowired
    final private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // TODO : change return type
    public UserEntity register(UserRegisterDTO userDTO){

        if(usersRepository.existsByEmail(userDTO.email())){
            throw new RuntimeException("User already exist");
        }

        UserEntity user = new UserEntity(userDTO);
        return usersRepository.save(user);
    }

    public List<UserDTO> getAll(){
        return usersRepository.findAllUsers().stream().map( userDAO -> new UserDTO(
                userDAO.getUuid(),
                userDAO.getName(),
                userDAO.getEmail(),
                UserRole.valueOf(userDAO.getRole())
        )).toList();
    }

    public UserDTO get(UUID userId){
        UserEntity user = usersRepository.findById(userId).orElseThrow( () -> new RuntimeException("User not found."));
        return new UserDTO(
                user.getUser_uuid(),
                user.getName(),
                user.getEmail(),
                UserRole.valueOf(user.getRole())
                );
    }

    public UserDTO update(UUID userId, UserUpdateDTO updatedUser){
        return usersRepository.findById(userId).map(
                user -> {
                    user.setName(updatedUser.name());
                    user.setEmail(updatedUser.email());
                    user.setRole(updatedUser.role().toString());
                    usersRepository.save(user);
                    return new UserDTO(userId, updatedUser.name(), updatedUser.email(), updatedUser.role());
                }
        ).orElseThrow( () -> new RuntimeException("User not found."));
    }

    public void delete(UUID userId){

        usersRepository.findById(userId).map(
                user -> {
                    usersRepository.delete(user);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new RuntimeException("User not found."));
    }


}
