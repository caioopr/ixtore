package com.caioop.ixtore.dtos;

import com.caioop.ixtore.entities.UserRole;

import java.util.UUID;

public record UserDTO(UUID user_uuid, String name, String email, UserRole role) {
}
