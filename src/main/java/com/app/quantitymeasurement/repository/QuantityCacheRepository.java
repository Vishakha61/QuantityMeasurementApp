package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class QuantityCacheRepository
        implements IQuantityRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuantityCacheRepository.class);
    private final List<QuantityMeasurementEntity> cache =
            new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {

        cache.add(entity);

        logger.info("Measurement stored in Cache Repository");
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        return new ArrayList<>(cache);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(
            String operation) {

        List<QuantityMeasurementEntity> result =
                new ArrayList<>();

        for (QuantityMeasurementEntity entity : cache) {

            if (entity.getOperation().equalsIgnoreCase(operation)) {

                result.add(entity);

            }
        }

        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(
            String measurementType) {

        List<QuantityMeasurementEntity> result =
                new ArrayList<>();

        for (QuantityMeasurementEntity entity : cache) {

            if (entity.getMeasurementType()
                    .equalsIgnoreCase(measurementType)) {

                result.add(entity);

            }
        }

        return result;
    }

    @Override
    public int getTotalCount() {

        return cache.size();
    }

    @Override
    public void deleteAll() {

        cache.clear();

        logger.info("Cache cleared.");
    }

    @Override
    public String getPoolStatistics() {

        return "Cache Repository - No Connection Pool";
    }

    @Override
    public void releaseResources() {

        logger.info("No resources to release for Cache Repository.");
    }
}