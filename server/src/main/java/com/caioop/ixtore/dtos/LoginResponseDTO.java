package com.caioop.ixtore.dtos;

import java.util.UUID;

public record LoginResponseDTO(UserDTO userInfo, String token) {
}
