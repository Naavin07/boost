package com.example.boost.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VoucherCode")
public class VoucherCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(unique = true)
    private String code;

    @ManyToOne
    private Recipient recipient;

    @ManyToOne
    private SpecialOffer specialOffer;

    private LocalDate localDate;

    private boolean used = false;

    private LocalDate dateOfUsage;
}



