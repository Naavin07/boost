package com.example.boost.controller;


import com.example.boost.entity.VoucherCode;
import com.example.boost.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/vouchers")
public class VoucherController {

   @Autowired
    private VoucherService voucherService;

    @PostMapping("/generate")
    public ResponseEntity<VoucherCode> generateVoucher(@RequestParam String email,
                                                       @RequestParam Long offerId,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate) {
        VoucherCode voucher = voucherService.generateVoucher(email, offerId, expirationDate);
        return ResponseEntity.ok(voucher);
    }

    @PostMapping("/validate")
    public ResponseEntity<VoucherCode> validateVoucher(@RequestParam String code,
                                                       @RequestParam String email) {
        VoucherCode voucher = voucherService.validateVoucher(code, email);
        return ResponseEntity.ok(voucher);
    }

    @GetMapping("/valid")
    public ResponseEntity<List<VoucherCode>> getValidVouchers(@RequestParam String email) {
        List<VoucherCode> vouchers = voucherService.getValidVouchers(email);
        return ResponseEntity.ok(vouchers);
    }
}