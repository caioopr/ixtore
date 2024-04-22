package com.caioop.ixtore.controllers;



import com.caioop.ixtore.dtos.UserDTO;

import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public UserEntity create(@RequestBody UserDTO user ){
        return usersService.create(user);
    }

}
