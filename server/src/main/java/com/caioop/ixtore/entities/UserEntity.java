package com.caioop.ixtore.entities;


import com.caioop.ixtore.dtos.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_uuid;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false,unique = true)
    private String email;

    @Column(name = "password", length = 16, nullable = false)
    private String password;

    @Column(name = "role", length = 16)
    private String role;

    @Column(name = "created_at")
    @Generated
    private Timestamp created_at;

    public UserEntity(UserRegisterDTO userDTO){
        this.name = userDTO.name();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.role = userDTO.role();
    }
}
