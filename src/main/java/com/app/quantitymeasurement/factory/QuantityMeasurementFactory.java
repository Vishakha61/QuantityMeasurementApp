package com.app.quantitymeasurement.factory;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.service.IQuantityService;
import com.app.quantitymeasurement.service.QuantityService;

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