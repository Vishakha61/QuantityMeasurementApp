package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(
            String operation
    );

    List<QuantityMeasurementEntity> findByThisMeasurementType(
            String measurementType
    );

    List<QuantityMeasurementEntity> findByIsErrorTrue();

    List<QuantityMeasurementEntity> findByCreatedAtAfter(
            LocalDateTime dateTime
    );
    List<QuantityMeasurementEntity> findByUserEmail(String userEmail);
    List<QuantityMeasurementEntity> findByUserEmailAndIsErrorTrue(String userEmail);

    List<QuantityMeasurementEntity> findByUserEmailAndOperation(
            String userEmail,
            String operation
    );

    List<QuantityMeasurementEntity> findByUserEmailAndThisMeasurementType(
            String userEmail,
            String measurementType
    );

    long countByUserEmailAndIsErrorTrue(String userEmail);
    long countByOperationAndIsErrorFalse(
            String operation
    );
}