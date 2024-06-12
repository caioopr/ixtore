package com.caioop.ixtore.controllers;

import com.caioop.ixtore.config.security.JwtService;
import com.caioop.ixtore.dtos.AuthenticationDTO;
import com.caioop.ixtore.dtos.LoginResponseDTO;
import com.caioop.ixtore.dtos.UserDTO;
import com.caioop.ixtore.dtos.UserRegisterDTO;
import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.entities.UserRole;
import com.caioop.ixtore.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        UserEntity user = (UserEntity) auth.getPrincipal();

        String token = jwtService.generateToken(user, (Map<String, Object>) new HashMap<>().put("user_id",user.getUser_uuid().toString()));

        return new LoginResponseDTO(
                new UserDTO(
                        user.getUser_uuid(),
                        user.getName(),
                        user.getEmail(),
                        UserRole.valueOf(user.getRole())
                ),
                token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody UserRegisterDTO data){

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserRegisterDTO newUser = new UserRegisterDTO(data.name(),data.email(), encryptedPassword, data.role());

        this.usersService.register(newUser);

    }

}
