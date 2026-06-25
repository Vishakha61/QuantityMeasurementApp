package com.quantitymeasurement.factory;

import com.quantitymeasurement.service.IQuantityService;
import com.quantitymeasurement.service.QuantityService;
import com.quantitymeasurement.controller.QuantityMeasurementController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementFactoryTest {

    @Test
    public void testGetServiceReturnsNotNull() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        assertNotNull(service);
    }

    @Test
    public void testGetServiceReturnsQuantityService() {
        IQuantityService service = QuantityMeasurementFactory.getService();
        assertTrue(service instanceof QuantityService);
    }

    @Test
    public void testGetControllerReturnsNotNull() {
        QuantityMeasurementController controller = QuantityMeasurementFactory.getController();
        assertNotNull(controller);
    }

    @Test
    public void testGetControllerReturnsQuantityMeasurementController() {
        QuantityMeasurementController controller = QuantityMeasurementFactory.getController();
        assertTrue(controller instanceof QuantityMeasurementController);
    }

    @Test
    public void testFactorySingletonPattern() {
        IQuantityService service1 = QuantityMeasurementFactory.getService();
        IQuantityService service2 = QuantityMeasurementFactory.getService();
        
        assertSame(service1, service2);
    }

    @Test
    public void testControllerSingletonPattern() {
        QuantityMeasurementController controller1 = QuantityMeasurementFactory.getController();
        QuantityMeasurementController controller2 = QuantityMeasurementFactory.getController();
        
        assertSame(controller1, controller2);
    }
}
