package com.caioop.ixtore.services;

import com.caioop.ixtore.entities.UserEntity;
import com.caioop.ixtore.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private final UsersRepository usersRepository;

    public AuthorizationService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            Optional<UserEntity> user = usersRepository.findByEmail(email);
            if(user.isEmpty()) {
                throw new UsernameNotFoundException("User not found.");
            }
            return user.get();
    }
}
