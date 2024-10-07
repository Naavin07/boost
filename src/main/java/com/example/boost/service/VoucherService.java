package com.example.boost.service;

import com.example.boost.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherService {

    @Autowired
    RecipientRepository recipientRepository;
}
