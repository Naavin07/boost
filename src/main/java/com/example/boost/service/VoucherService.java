package com.example.boost.service;

import com.example.boost.entity.Recipient;
import com.example.boost.entity.SpecialOffer;
import com.example.boost.entity.VoucherCode;
import com.example.boost.repository.RecipientRepository;
import com.example.boost.repository.SpecialOfferRepository;
import com.example.boost.repository.VoucherCodeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class VoucherService {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private VoucherCodeRepository voucherCodeRepository;

    @Autowired
    private SpecialOfferRepository  specialOfferRepository;

    public VoucherCode generateVoucher(String email, Long specialOfferId, LocalDate expirationDate) {
        if (email == null || specialOfferId == null || expirationDate == null) {
            throw new IllegalArgumentException("Email, specialOfferId, and expirationDate cannot be null");
        }

        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date cannot be in the past");
        }

        SpecialOffer specialOffer = specialOfferRepository.findById(specialOfferId)
                .orElseThrow(() -> new EntityNotFoundException("Special Offer not found for id: " + specialOfferId));

        Recipient recipient = recipientRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Recipient not found with email: " + email));

        String uniqueCode;
        do {
            uniqueCode = UUID.randomUUID().toString().substring(0, 8);
        } while (voucherCodeRepository.existsByCode(uniqueCode));  // Ensure unique code

        VoucherCode voucherCode = new VoucherCode();
        voucherCode.setCode(uniqueCode);
        voucherCode.setRecipient(recipient);
        voucherCode.setSpecialOffer(specialOffer);
        voucherCode.setExpirationDate(expirationDate);

        voucherCodeRepository.save(voucherCode);

        return voucherCode;
    }
}

