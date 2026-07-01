package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityRepository {

    // Save measurement
    void save(QuantityMeasurementEntity entity);

    // Get all measurements
    List<QuantityMeasurementEntity> getAllMeasurements();

    // Get by operation
    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation);

    // Get by measurement type
    List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType);

    // Total measurements
    int getTotalCount();

    // Delete all
    void deleteAll();

    // Connection pool statistics (Database repository only)
    default String getPoolStatistics() {
        return "Connection pool not available";
    }

    // Release resources if required
    default void releaseResources() {
        // Default implementation
    }
}