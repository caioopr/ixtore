package com.caioop.ixtore.dtos;

import java.util.UUID;

public record UserDTO(UUID user_uuid, String name, String email, String role) {
}
