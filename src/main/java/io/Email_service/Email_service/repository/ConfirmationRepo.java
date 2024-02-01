package io.Email_service.Email_service.repository;

import io.Email_service.Email_service.model.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepo extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);
}
