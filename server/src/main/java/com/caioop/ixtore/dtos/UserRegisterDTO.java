package com.caioop.ixtore.dtos;

import com.caioop.ixtore.entities.UserRole;

public record UserRegisterDTO(String name, String email, String password, UserRole role) {
}