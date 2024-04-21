package com.caioop.ixtore.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratedColumn;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class User {
    @Id
    @Column(name = "user_uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_uuid;

    @Column(name = "name", length = 50)
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 16)
    private String password;

    @Column(name = "role", length = 16)
    private String role;

    @Column(name = "created_at", insertable = false)
    @Generated
    private Timestamp created_at;

}
