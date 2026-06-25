package com.quantitymeasurement.controller;

import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.enums.LengthUnit;
import com.quantitymeasurement.service.IQuantityService;
import com.quantitymeasurement.factory.QuantityMeasurementFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementControllerTest {

    @Test
    public void testControllerIsNotNull() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        assertNotNull(controller);
    }

    @Test
    public void testControllerInitializationWithService() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        assertNotNull(controller);
    }

    @Test
    public void testAddQuantities() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = controller.add(q1, q2, LengthUnit.FEET);

        assertNotNull(result);
        assertEquals(2.0, result.getValue(), 0.001);
    }

    @Test
    public void testSubtractQuantities() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = controller.subtract(q1, q2, LengthUnit.FEET);

        assertNotNull(result);
        assertEquals(1.0, result.getValue(), 0.001);
    }

    @Test
    public void testDivideQuantities() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        Quantity<LengthUnit> q1 = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = controller.divide(q1, q2);

        assertEquals(2.0, result, 0.001);
    }

    @Test
    public void testConvertQuantity() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        Quantity<LengthUnit> quantity = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = controller.convert(quantity, LengthUnit.FEET);

        assertNotNull(result);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
}
