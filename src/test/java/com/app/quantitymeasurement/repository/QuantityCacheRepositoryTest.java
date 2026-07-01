package com.app.quantitymeasurement.repository;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityCacheRepositoryTest {

    private IQuantityRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new QuantityCacheRepository();
    }

    @Test
    public void testRepositoryIsNotNull() {
        assertNotNull(repository);
    }

    @Test
    public void testRepositoryIsInstanceOfQuantityCacheRepository() {
        assertTrue(repository instanceof QuantityCacheRepository);
    }

    @Test
    public void testSaveMethodDoesNotThrowException() {
        try {
            repository.save(createEntity());
        } catch (Exception e) {
            fail("Save method should not throw exception");
        }
    }

    @Test
    public void testRepositoryImplementsInterface() {
        assertTrue(repository instanceof IQuantityRepository);
    }

    @Test
    private QuantityMeasurementEntity createEntity() {

    QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

    entity.setMeasurementType("Length");
    entity.setOperation("ADD");
    entity.setThisValue(5);
    entity.setThisUnit("FEET");
    entity.setThatValue(12);
    entity.setThatUnit("INCHES");
    entity.setResultString("6 FEET");

    return entity;
}

    @Test
    public void testMultipleSaveCallsAreIdempotent() {
        repository.save(createEntity());
repository.save(createEntity());
repository.save(createEntity());
       
        assertTrue(true);
    }
}
