package com.caioop.ixtore.dtos;

import com.caioop.ixtore.entities.UserRole;

public record UserUpdateDTO(String name, String email, UserRole role) {
}
