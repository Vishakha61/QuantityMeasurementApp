package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084); // 1 cm = 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public double toFeet() {
            return unit.toFeet(value);
        }

        // UC6: result will be in unit of first operand (this.unit)
        public QuantityLength add(QuantityLength other) {
            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            double sumInFeet = this.toFeet() + other.toFeet();
            double resultValue = this.unit.fromFeet(sumInFeet);

            return new QuantityLength(resultValue, this.unit);
        }

        // UC7: result will be in explicitly specified targetUnit
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumInFeet = this.toFeet() + other.toFeet();
            double resultValue = targetUnit.fromFeet(sumInFeet);

            return new QuantityLength(resultValue, targetUnit);
        }

        // UC7: static add method (useful for test cases)
        public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {
            if (a == null || b == null)
                throw new IllegalArgumentException("Lengths cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumInFeet = a.toFeet() + b.toFeet();
            double resultValue = targetUnit.fromFeet(sumInFeet);

            return new QuantityLength(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj == null || this.getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        // UC6
        QuantityLength result1 = length1.add(length2);

        // UC7
        QuantityLength result2 = length1.add(length2, LengthUnit.YARDS);

        System.out.println("Input: " + length1 + " + " + length2);
        System.out.println("UC6 Output (default unit): " + result1);
        System.out.println("UC7 Output (target YARDS): " + result2);
    }
}