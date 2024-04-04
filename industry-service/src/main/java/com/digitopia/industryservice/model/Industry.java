package com.digitopia.industryservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "industry")
@Data
@NoArgsConstructor
public class Industry extends BaseEntity {

    @Column(nullable = false)
    private String industryName;
    @Column(nullable = false)
    private String normalizedIndustryName;
    @ManyToOne
    @JoinColumn(name = "parent_industry_id")
    private Industry parentIndustry;
    @OneToMany(mappedBy = "parentIndustry")
    private List<Industry> subIndustry;
    @Column(name = "user_id")
    private List<UUID> users;

    @Builder(builderMethodName = "industryBuilder")
    public Industry(UUID id, LocalDateTime created, LocalDateTime updated, UUID createdBy, UUID updatedBy, String industryName, String normalizedIndustryName, Industry parentIndustry, List<Industry> subIndustry, List<UUID> users) {
        super(id, created, updated, createdBy, updatedBy);
        this.industryName = industryName;
        this.normalizedIndustryName = normalizedIndustryName;
        this.parentIndustry = parentIndustry;
        this.subIndustry = subIndustry;
        this.users = users;
    }
}
