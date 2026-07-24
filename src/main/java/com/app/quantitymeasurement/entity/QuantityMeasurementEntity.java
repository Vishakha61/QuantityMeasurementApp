package com.app.quantitymeasurement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // First Quantity

    @Column(nullable = false)
    private Double thisValue;

    @Column(nullable = false, length = 30)
    private String thisUnit;

    @Column(nullable = false, length = 30)
    private String thisMeasurementType;

    // Second Quantity

    @Column(nullable = false)
    private Double thatValue;

    @Column(nullable = false, length = 30)
    private String thatUnit;

    @Column(nullable = false, length = 30)
    private String thatMeasurementType;

    // Operation

    @Column(nullable = false, length = 30)
    private String operation;

    // Result

    private Double resultValue;

    @Column(length = 30)
    private String resultUnit;

    @Column(length = 30)
    private String resultMeasurementType;

    @Column(length = 255)
    private String resultString;

    // Error

    @Column(length = 255)
    private String errorMessage;

    @Column(nullable = false)
    private boolean isError = false;

    // Audit

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();
    }


}