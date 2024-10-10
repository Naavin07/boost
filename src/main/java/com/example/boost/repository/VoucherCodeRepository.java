package com.example.boost.repository;

import com.example.boost.entity.VoucherCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoucherCodeRepository extends JpaRepository<VoucherCode, Long> {
    List<VoucherCode> findAllByRecipientEmailAndUsedFalse(String email);
    Optional<VoucherCode> findByCodeAndRecipientEmail(String code, String email);
}
