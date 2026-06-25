package com.quantitymeasurement.model;

import com.quantitymeasurement.enums.ArithmeticOperation;
import com.quantitymeasurement.enums.TemperatureUnit;
import com.quantitymeasurement.interfaces.IMeasurable;

import java.util.Objects;
public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(
            double value,
            U unit
    ) {

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        if (Double.isNaN(value)
                || Double.isInfinite(value)) {
            throw new IllegalArgumentException(
                    "Invalid value"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double roundToTwoDecimals(
            double value
    ) {
        return Math.round(value * 100.0)
                / 100.0;
    }

    public Quantity<U> convertTo(
            U targetUnit
    ) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double convertedValue;

        if (unit instanceof TemperatureUnit
                && targetUnit instanceof TemperatureUnit) {

            convertedValue =
                    ((TemperatureUnit) unit)
                            .convertTo(
                                    value,
                                    (TemperatureUnit) targetUnit
                            );

        } else {

            double baseValue =
                    unit.convertToBaseUnit(
                            value
                    );

            convertedValue =
                    targetUnit.convertFromBaseUnit(
                            baseValue
                    );
        }

        convertedValue =
                roundToTwoDecimals(
                        convertedValue
                );

        return new Quantity<>(
                convertedValue,
                targetUnit
        );
    }

    public Quantity<U> add(
            Quantity<U> other
    ) {
        return add(other, this.unit);
    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.ADD
                );

        double convertedResult =
                targetUnit.convertFromBaseUnit(
                        resultBase
                );

        convertedResult =
                roundToTwoDecimals(
                        convertedResult
                );

        return new Quantity<>(
                convertedResult,
                targetUnit
        );
    }

    public Quantity<U> subtract(
            Quantity<U> other
    ) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT
                );

        double convertedResult =
                targetUnit.convertFromBaseUnit(
                        resultBase
                );

        convertedResult =
                roundToTwoDecimals(
                        convertedResult
                );

        return new Quantity<>(
                convertedResult,
                targetUnit
        );
    }

    public double divide(
            Quantity<U> other
    ) {

        validateArithmeticOperands(
                other,
                null,
                false
        );

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE
        );
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Other quantity cannot be null"
            );
        }

        if (targetUnitRequired
                && targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        if (this.unit.getClass()
                != other.unit.getClass()) {

            throw new IllegalArgumentException(
                    "Incompatible quantity types"
            );
        }

        if (Double.isNaN(this.value)
                || Double.isInfinite(this.value)
                || Double.isNaN(other.value)
                || Double.isInfinite(other.value)) {

            throw new IllegalArgumentException(
                    "Invalid quantity value"
            );
        }
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        this.unit.validateOperationSupport(
                operation.name()
        );

        other.unit.validateOperationSupport(
                operation.name()
        );

        double thisBaseValue =
                this.unit.convertToBaseUnit(
                        this.value
                );

        double otherBaseValue =
                other.unit.convertToBaseUnit(
                        other.value
                );

        return operation.compute(
                thisBaseValue,
                otherBaseValue
        );
    }

    @Override
    public boolean equals(
            Object obj
    ) {

        if (this == obj) {
            return true;
        }

        if (obj == null
                || getClass()
                != obj.getClass()) {
            return false;
        }

        Quantity<?> other =
                (Quantity<?>) obj;

        if (this.unit.getClass()
                != other.unit.getClass()) {
            return false;
        }

        double thisBaseValue =
                this.unit.convertToBaseUnit(
                        this.value
                );

        double otherBaseValue =
                other.unit.convertToBaseUnit(
                        other.value
                );

        return Math.abs(
                thisBaseValue
                        - otherBaseValue
        ) < EPSILON;
    }

    @Override
    public int hashCode() {

        double baseValue =
                unit.convertToBaseUnit(
                        value
                );

        long rounded =
                Math.round(
                        baseValue / EPSILON
                );

        return Objects.hash(
                rounded,
                unit.getClass()
        );
    }

    @Override
    public String toString() {

        return "Quantity{" +
                "value=" + value +
                ", unit=" +
                unit.getUnitName() +
                '}';
    }
}