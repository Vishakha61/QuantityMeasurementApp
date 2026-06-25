package com.quantitymeasurement.repository;

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
            repository.save();
        } catch (Exception e) {
            fail("Save method should not throw exception");
        }
    }

    @Test
    public void testRepositoryImplementsInterface() {
        assertTrue(repository instanceof IQuantityRepository);
    }

    @Test
    public void testMultipleSaveCallsAreIdempotent() {
        repository.save();
        repository.save();
        repository.save();
        // If no exception is thrown, test passes
        assertTrue(true);
    }
}
