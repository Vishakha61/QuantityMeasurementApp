
package com.app.quantitymeasurement.unit;

public interface IMeasurable {

    String getUnitName();

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    SupportArithmetic ARITHMETIC_SUPPORT =
            () -> true;

    default boolean supportArithmetic() {
        return ARITHMETIC_SUPPORT.isSupported();
    }

    default void validateOperationSupport(
            String operation
    ) {
        // default: all operations supported
    }
}