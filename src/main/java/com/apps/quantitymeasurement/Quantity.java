package com.apps.quantitymeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPSILON) {
                throw new ArithmeticException(
                        "Division by zero"
                );
            }
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(
                DoubleBinaryOperator operation
        ) {
            this.operation = operation;
        }

        public double compute(
                double a,
                double b
        ) {
            return operation.applyAsDouble(a, b);
        }
    }

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

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        baseValue
                );

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
                unit.convertToBaseUnit(value);

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