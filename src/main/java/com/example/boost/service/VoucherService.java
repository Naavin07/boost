package com.example.boost.service;

import com.example.boost.entity.Recipient;
import com.example.boost.entity.SpecialOffer;
import com.example.boost.entity.VoucherCode;
import com.example.boost.repository.RecipientRepository;
import com.example.boost.repository.SpecialOfferRepository;
import com.example.boost.repository.VoucherCodeRepository;
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

    @Transactional
    public VoucherCode generateVoucher(String email, Long specialOfferId, LocalDate expirationDate){
            SpecialOffer specialOffer = specialOfferRepository.findById(specialOfferId).orElseThrow();
            Recipient recipient = recipientRepository.findByEmail(email).orElseThrow();
            String uniqueCode = UUID.randomUUID().toString().substring(0, 8);

            VoucherCode voucherCode = new VoucherCode();
            voucherCode.setCode(uniqueCode);
            voucherCode.setRecipient(recipient);
            voucherCode.setSpecialOffer(specialOffer);
            voucherCode.setExpirationDate(expirationDate);
            voucherCodeRepository.save(voucherCode);

            return voucherCode;
    }

    @Transactional
    public VoucherCode validateVoucher(String code, String email) {
        VoucherCode voucherCode = voucherCodeRepository.findByCodeAndRecipientEmail(code, email)
                .orElseThrow(() -> new RuntimeException("Invalid voucher or email"));

        if (voucherCode.isUsed() || voucherCode.getExpirationDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Voucher is either expired or already used");
        }

        voucherCode.setUsed(true);
        voucherCode.setDateOfUsage(LocalDate.now());
        voucherCodeRepository.save(voucherCode);

        return voucherCode;
    }


    public List<VoucherCode> getValidVouchers(String email){
        return voucherCodeRepository.findAllByRecipientEmailAndUsedFalse(email);
    }
}
