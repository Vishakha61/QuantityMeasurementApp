package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.interfaces.IMeasurable;
import com.app.quantitymeasurement.entity.Quantity;
import com.app.quantitymeasurement.repository.IQuantityRepository;
import com.app.quantitymeasurement.repository.QuantityCacheRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.util.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityService implements IQuantityService {

    private final IQuantityRepository repository;
        private static final Logger logger = LoggerFactory.getLogger(QuantityService.class);
        public QuantityService() {

        if ("database".equalsIgnoreCase(DatabaseConfig.getRepositoryType())) {
                repository = QuantityMeasurementDatabaseRepository.getInstance();
        } else {
                repository = new QuantityCacheRepository();
        }
        }

    @Override
    public <U extends IMeasurable>
    Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result =
                q1.add(q2, targetUnit);

        QuantityMeasurementEntity entity =
                createEntity(
                        "Quantity",
                        "ADD",
                        q1,
                        q2,
                        result);

        repository.save(entity);
        logger.info("ADD operation saved successfully.");
        return result;
    }

    @Override
    public <U extends IMeasurable>
    Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result =
                q1.subtract(q2, targetUnit);

        QuantityMeasurementEntity entity =
                createEntity(
                        "Quantity",
                        "SUBTRACT",
                        q1,
                        q2,
                        result);

        repository.save(entity);
        logger.info("SUBTRACT operation saved successfully.");
        return result;
    }

    @Override
    public <U extends IMeasurable>
    double divide(
            Quantity<U> q1,
            Quantity<U> q2) {

        double result =
                q1.divide(q2);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setMeasurementType("Quantity");
        entity.setOperation("DIVIDE");

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnit().toString());

        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnit().toString());

        entity.setResultValue(result);
        entity.setResultUnit("NUMBER");
        entity.setResultString(String.valueOf(result));

        repository.save(entity);
        logger.info("DIVIDE operation saved successfully.");

        return result;
    }

    @Override
    public <U extends IMeasurable>
    Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit) {

        Quantity<U> result =
                quantity.convertTo(targetUnit);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setMeasurementType("Quantity");
        entity.setOperation("CONVERT");

        entity.setThisValue(quantity.getValue());
        entity.setThisUnit(quantity.getUnit().toString());

        entity.setThatValue(0);
        entity.setThatUnit("-");

        entity.setResultValue(result.getValue());
        entity.setResultUnit(result.getUnit().toString());
        entity.setResultString(result.toString());

        repository.save(entity);
        logger.info("CONVERT operation saved successfully.");
        return result;
    }

    private <U extends IMeasurable>
    QuantityMeasurementEntity createEntity(
            String type,
            String operation,
            Quantity<U> q1,
            Quantity<U> q2,
            Quantity<U> result) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setMeasurementType(type);
        entity.setOperation(operation);

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnit().toString());

        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnit().toString());

        entity.setResultValue(result.getValue());
        entity.setResultUnit(result.getUnit().toString());
        entity.setResultString(result.toString());

        return entity;
    }
}