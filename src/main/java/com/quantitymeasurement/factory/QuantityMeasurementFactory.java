package com.quantitymeasurement.factory;

import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.service.IQuantityService;
import com.quantitymeasurement.service.QuantityService;

public class QuantityMeasurementFactory {

    private static final IQuantityService service =
            new QuantityService();

    private static final QuantityMeasurementController controller =
            new QuantityMeasurementController(service);

    private QuantityMeasurementFactory() {
    }

    public static IQuantityService getService() {
        return service;
    }

    public static QuantityMeasurementController getController() {
        return controller;
    }
}