package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static
    QuantityMeasurementCacheRepository
            instance;

    private final List<
            QuantityMeasurementEntity
            > cache;

    private QuantityMeasurementCacheRepository() {

        cache = new ArrayList<>();
    }

    public static
    QuantityMeasurementCacheRepository
    getInstance() {

        if (instance == null) {

            instance =
                    new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    @Override

    public void save(

            QuantityMeasurementEntity entity
    ) {

        cache.add(entity);
    }

    @Override

    public List<
            QuantityMeasurementEntity
            > findAll() {

        return cache;
    }
}