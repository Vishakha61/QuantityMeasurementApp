package com.quantitymeasurement.model;

import com.quantitymeasurement.interfaces.IMeasurable;

public class QuantityMeasurementEntity<U extends IMeasurable> {

    private double value;
    private U unit;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(
            double value,
            U unit
    ) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(
            double value
    ) {
        this.value = value;
    }

    public U getUnit() {
        return unit;
    }

    public void setUnit(
            U unit
    ) {
        this.unit = unit;
    }
}