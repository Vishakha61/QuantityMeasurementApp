package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.Quantity;
import com.app.quantitymeasurement.unit.LengthUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityServiceTest {

    private IQuantityService quantityService;

    @BeforeEach
    public void setUp() {
        quantityService = new QuantityService();
    }

    @Test
    public void testAddTwoQuantities() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        
        Quantity<LengthUnit> result = quantityService.add(q1, q2, LengthUnit.FEET);
        
        assertNotNull(result);
        assertEquals(2.0, result.getValue(), 0.001);
    }

    @Test
    public void testSubtractTwoQuantities() {
        Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        
        Quantity<LengthUnit> result = quantityService.subtract(q1, q2, LengthUnit.FEET);
        
        assertNotNull(result);
        assertEquals(1.0, result.getValue(), 0.001);
    }

    @Test
    public void testDivideTwoQuantities() {
        Quantity<LengthUnit> q1 = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        
        double result = quantityService.divide(q1, q2);
        
        assertEquals(2.0, result, 0.001);
    }

    @Test
    public void testConvertQuantity() {
        Quantity<LengthUnit> quantity = new Quantity<>(1.0, LengthUnit.FEET);
        
        Quantity<LengthUnit> result = quantityService.convert(quantity, LengthUnit.FEET);
        
        assertNotNull(result);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testServiceIsNotNull() {
        assertNotNull(quantityService);
    }
}
