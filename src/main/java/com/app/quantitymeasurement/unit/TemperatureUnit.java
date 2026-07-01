package com.app.quantitymeasurement.unit;
import com.app.quantitymeasurement.interfaces.IMeasurable;
import com.app.quantitymeasurement.interfaces.SupportsArithmetic;
import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
            "Celsius",
            celsius -> celsius,
            celsius -> celsius
    ),

    FAHRENHEIT(
            "Fahrenheit",
            fahrenheit -> (fahrenheit - 32) * 5 / 9,
            celsius -> (celsius * 9 / 5) + 32
    ),

    KELVIN(
            "Kelvin",
            kelvin -> kelvin - 273.15,
            celsius -> celsius + 273.15
    );

    private final String unitName;

    private final Function<Double, Double>
            convertToCelsius;

    private final Function<Double, Double>
            convertFromCelsius;

    private static final SupportsArithmetic
            supportsArithmetic =
            () -> false;

    TemperatureUnit(
            String unitName,
            Function<Double, Double> convertToCelsius,
            Function<Double, Double> convertFromCelsius
    ) {

        this.unitName = unitName;
        this.convertToCelsius = convertToCelsius;
        this.convertFromCelsius =
                convertFromCelsius;
    }

    @Override
    public double getConversionFactor() {

        return 1.0;
    }

    @Override
    public double convertToBaseUnit(
            double value
    ) {

        return convertToCelsius.apply(
                value
        );
    }

    @Override
    public double convertFromBaseUnit(
            double baseValue
    ) {

        return convertFromCelsius.apply(
                baseValue
        );
    }

    /**
     * Temperature specific conversion.
     */
    public double convertTo(
            double value,
            TemperatureUnit targetUnit
    ) {

        double celsiusValue =
                this.convertToBaseUnit(
                        value
                );

        return targetUnit
                .convertFromBaseUnit(
                        celsiusValue
                );
    }

    @Override
    public String getUnitName() {

        return unitName;
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