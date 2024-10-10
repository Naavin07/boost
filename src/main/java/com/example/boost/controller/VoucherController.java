package com.example.boost.controller;

import com.example.boost.entity.VoucherCode;
import com.example.boost.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {

   @Autowired
    private VoucherService voucherService;

   @GetMapping("/valid")
   public ResponseEntity<List<VoucherCode>> getValidVouchers(@RequestParam String email) {
       List<VoucherCode> vouchers = voucherService.getValidVouchers(email);
       return ResponseEntity.ok(vouchers);
   }
  /* @PostMapping
    public ResponseEntity<VoucherCode> generateVoucher(@RequestParam String email,
                                                       @RequestParam Long offerId,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
       //VoucherCode voucher = voucherService.generateVoucher(email, offerId, expirationDate);
      // return ResponseEntity.ok(voucher);
   }*/
}
