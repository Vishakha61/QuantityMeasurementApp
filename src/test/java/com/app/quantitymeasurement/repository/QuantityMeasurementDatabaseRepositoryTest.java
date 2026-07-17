package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class QuantityMeasurementRepositoryTest {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Test
    void repositoryShouldLoad() {

        assertNotNull(repository);
    }

    @Test
    void shouldSaveEntity() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setThisValue(1.0);
        entity.setThisUnit("FEET");
        entity.setThisMeasurementType("LENGTH");

        entity.setThatValue(12.0);
        entity.setThatUnit("INCHES");
        entity.setThatMeasurementType("LENGTH");

        entity.setOperation("COMPARE");

        entity.setResultString("true");

        entity.setError(false);

        QuantityMeasurementEntity saved =
                repository.save(entity);

        assertNotNull(saved.getId());
    }
}
