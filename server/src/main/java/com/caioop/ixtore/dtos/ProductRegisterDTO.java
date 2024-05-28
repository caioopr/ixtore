package com.caioop.ixtore.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRegisterDTO(
        String code,
        String name,
        BigDecimal price,
        String description,
        Double weight,
        UUID fk_user_uuid,
        String supplier
) {
}
