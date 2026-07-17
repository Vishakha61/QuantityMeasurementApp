package com.app.quantitymeasurement.unit;

import java.util.function.Function;

public enum TemperatureUnit
        implements IMeasurable {

    CELSIUS(
            "CELSIUS",
            celsius -> celsius,
            celsius -> celsius
    ),

    FAHRENHEIT(
            "FAHRENHEIT",

            fahrenheit ->
                    (fahrenheit - 32)
                            * 5 / 9,

            celsius ->
                    (celsius * 9 / 5)
                            + 32
    ),

    KELVIN(
            "KELVIN",

            kelvin ->
                    kelvin - 273.15,

            celsius ->
                    celsius + 273.15
    );

    private final String unitName;

    private final Function<Double, Double>
            toCelsius;

    private final Function<Double, Double>
            fromCelsius;

    private final SupportsArithmetic
            supportsArithmetic =
            () -> false;

    TemperatureUnit(
            String unitName,

            Function<Double, Double>
                    toCelsius,

            Function<Double, Double>
                    fromCelsius
    ) {

        this.unitName = unitName;

        this.toCelsius =
                toCelsius;

        this.fromCelsius =
                fromCelsius;
    }

    @Override
    public double getConversionFactor() {

        return 1.0;
    }

    @Override
    public double convertToBaseUnit(
            double value
    ) {

        return toCelsius.apply(
                value
        );
    }

    @Override
    public double convertFromBaseUnit(
            double baseValue
    ) {

        return fromCelsius.apply(
                baseValue
        );
    }

    @Override
    public String getUnitName() {

        return unitName;
    }

    // ---------- UC15 additions ----------

    @Override
    public String getMeasurementType() {

        return "TEMPERATURE";
    }

    @Override
    public IMeasurable getUnitInstance(
            String unitName
    ) {

        return TemperatureUnit.valueOf(
                unitName
        );
    }

    @Override
    public boolean supportsArithmetic() {

        return supportsArithmetic
                .isSupported();
    }

    @Override
    public void validateOperationSupport(
            String operation
    ) {

        throw new UnsupportedOperationException(

                "Temperature does not support "

                        + operation

                        + " operation"

        );
    }
}