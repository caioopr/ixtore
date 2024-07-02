package com.caioop.ixtore.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductUpdateDTO(
        String name,
        BigDecimal price,
        String description,
        Double weight,
        String supplier
) {
}
