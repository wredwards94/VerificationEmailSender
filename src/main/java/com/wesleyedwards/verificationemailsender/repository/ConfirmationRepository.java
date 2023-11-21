package com.wesleyedwards.verificationemailsender.repository;

import com.wesleyedwards.verificationemailsender.entities.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);
}
