package com.digitopia.userservice.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String normalizedName;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;


    @Builder(builderMethodName = "userBuilder")
    public User(UUID id, LocalDateTime created, LocalDateTime updated, UUID createdBy, UUID updatedBy, String fullName, String email, String normalizedName, Role role, Status status) {
        super(id, created, updated, createdBy, updatedBy);
        this.fullName = fullName;
        this.email = email;
        this.normalizedName = normalizedName;
        this.role = role;
        this.status = status;
    }
}
