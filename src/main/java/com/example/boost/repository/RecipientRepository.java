package com.example.boost.repository;

import com.example.boost.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
    Optional<Recipient> findByEmail(String email);
}
