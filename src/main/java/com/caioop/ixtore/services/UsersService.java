package com.caioop.ixtore.services;

import com.caioop.ixtore.dtos.UserDTO;
import com.caioop.ixtore.dtos.UserRegisterDTO;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;


@Service
public class UsersService {

    @Autowired
    final private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity register(UserRegisterDTO userDTO){

        if(usersRepository.existsByEmail(userDTO.email())){
            throw new RuntimeException("User already exits");
        }

        UserEntity user = new UserEntity(userDTO);
        return usersRepository.save(user);
    }

    public List<UserDTO> getAll(){
        return usersRepository.findAllUsers().stream().map( userDAO -> new UserDTO(
                convertBytesToUUID(userDAO.getUuid()),
                userDAO.getName(),
                userDAO.getEmail(),
                userDAO.getRole()
        )).toList();
    }

    public UserDTO get(UUID userId){
        UserEntity user = usersRepository.findById(userId).orElseThrow( () -> new RuntimeException("User not found."));
        return new UserDTO(
                user.getUser_uuid(),
                user.getName(),
                user.getEmail(),
                user.getRole()
                );
    }

    public void update(UUID userId){
        return;
    }

    public void delete(UUID userId){
        return;
    }

    private UUID convertBytesToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

}
