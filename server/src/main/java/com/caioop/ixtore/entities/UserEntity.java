package com.caioop.ixtore.entities;


import com.caioop.ixtore.dtos.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_users")
public class UserEntity implements UserDetails,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_uuid;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false,unique = true)
    private String email;

    @Column(name = "password", nullable = false)
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
        this.role = userDTO.role().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role.equals(UserRole.ADMIN.toString())) {
            return List.of(
                    new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("EMPLOYEE"),
                    new SimpleGrantedAuthority("USER"));
        } else if(this.role.equals(UserRole.EMPLOYEE.toString())){
            return List.of(
                    new SimpleGrantedAuthority("EMPLOYEE"),
                    new SimpleGrantedAuthority("USER"));
        } else {
            return List.of( new SimpleGrantedAuthority("USER"));
        }

    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
