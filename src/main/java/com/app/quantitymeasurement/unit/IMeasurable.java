
package com.app.quantitymeasurement.unit;

public interface IMeasurable {

    SupportsArithmetic supportsArithmetic =
            () -> true;

    double getConversionFactor();

    double convertToBaseUnit(
            double value
    );

    double convertFromBaseUnit(
            double baseValue
    );

    String getUnitName();

    // ADD FOR UC15

    String getMeasurementType();

    IMeasurable getUnitInstance(
            String unitName
    );

    default boolean supportsArithmetic() {

        return supportsArithmetic
                .isSupported();
    }

    default void validateOperationSupport(
            String operation
    ) {

        // Default:
        // all units support arithmetic

    }
}