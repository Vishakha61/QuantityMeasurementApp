package com.app.quantitymeasurement.unit;

public enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // value ko base unit FEET me convert karega
    public double convertToBaseUnit(double value) {

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid length value");
        }

        return value * conversionFactor;
    }

    // base unit FEET se current unit me convert karega
    public double convertFromBaseUnit(double baseValue) {

        if (Double.isNaN(baseValue) || Double.isInfinite(baseValue)) {
            throw new IllegalArgumentException("Invalid length value");
        }

        return baseValue / conversionFactor;
    }
    @Override
    public String getUnitName() {
        return this.name();
    }
}