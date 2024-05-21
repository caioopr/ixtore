package com.caioop.ixtore.controllers;



import com.caioop.ixtore.dtos.UserDTO;

import com.caioop.ixtore.dtos.UserRegisterDTO;
import com.caioop.ixtore.dtos.UserUpdateDTO;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    final private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity register(@RequestBody UserRegisterDTO user ){
        return usersService.register(user);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO get(@PathVariable("id") UUID id){
        return usersService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAll(){
        return usersService.getAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable("id") UUID id, @RequestBody UserUpdateDTO updatedUser){
        return usersService.update(id,updatedUser);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        usersService.delete(id);
    }

}
