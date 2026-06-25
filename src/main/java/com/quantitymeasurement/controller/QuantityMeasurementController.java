package com.quantitymeasurement.controller;

import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.service.IQuantityService;

public class QuantityMeasurementController {

    private final IQuantityService service;

    public QuantityMeasurementController(
            IQuantityService service) {

        this.service = service;
    }

    public <U extends IMeasurable>
    Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        return service.add(
                q1,
                q2,
                targetUnit);
    }

    public <U extends IMeasurable>
    Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        return service.subtract(
                q1,
                q2,
                targetUnit);
    }

    public <U extends IMeasurable>
    double divide(
            Quantity<U> q1,
            Quantity<U> q2) {

        return service.divide(q1,q2);
    }

    public <U extends IMeasurable>
    Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit) {

        return service.convert(
                quantity,
                targetUnit);
    }
}