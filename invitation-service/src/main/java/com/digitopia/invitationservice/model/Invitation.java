package com.digitopia.invitationservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "invitation")
@NoArgsConstructor
public class Invitation extends BaseEntity{
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private String code;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder(builderMethodName = "invitationBuilder")
    public Invitation(UUID id, LocalDateTime created, LocalDateTime updated, UUID createdBy, UUID updatedBy, String message, String code, Status status) {
        super(id, created, updated, createdBy, updatedBy);
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
