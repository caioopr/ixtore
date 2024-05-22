package com.caioop.ixtore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer product_id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price; // 000.000.000.000,0000

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "fk_user_uuid")
    private UUID fk_user_uuid;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "created_at")
    @Generated
    private Timestamp created_at;
}
