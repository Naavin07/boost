package com.example.boost.service;

import com.example.boost.entity.VoucherCode;
import com.example.boost.repository.RecipientRepository;
import com.example.boost.repository.SpecialOfferRepository;
import com.example.boost.repository.VoucherCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VoucherService {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private VoucherCodeRepository voucherCodeRepository;

    @Autowired
    private SpecialOfferRepository  specialOfferRepository;


    public List<VoucherCode> getValidVouchers(String email){
        return voucherCodeRepository.findAllByRecipientEmailAndUsedFalse(email);
    }
}
