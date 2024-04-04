package com.digitopia.invitationservice.repository;

import com.digitopia.invitationservice.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,UUID> {
}
