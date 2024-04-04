package com.digitopia.industryservice.repository;

import com.digitopia.industryservice.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IndustryRepository extends JpaRepository<Industry, UUID> {
}
