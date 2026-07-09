package com.app.quantitymeasurement.unit;

public enum TemperatureUnit
        implements IMeasurable {

    CELSIUS,
    FAHRENHEIT;

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(
            double value
    ) {

        if (this == CELSIUS) {
            return value;
        }

        return (value - 32) * 5 / 9;
    }

    @Override
    public double convertFromBaseUnit(
            double baseValue
    ) {

        if (this == CELSIUS) {
            return baseValue;
        }

        return (baseValue * 9 / 5) + 32;
    }

    @Override
    public boolean supportArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(
            String operation
    ) {

        throw new UnsupportedOperationException(
                getUnitName()
                        + " does not support "
                        + operation
                        + " operations"
        );
    }
}
