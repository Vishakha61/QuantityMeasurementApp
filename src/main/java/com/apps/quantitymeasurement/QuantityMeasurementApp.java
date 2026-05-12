package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Base unit = FEET
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

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
                throw new IllegalArgumentException("Value must be a finite number");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        public double toFeet() {
            return unit.toFeet(value);
        }

        // UC5: Convert current object to target unit
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseFeet = this.toFeet();
            double convertedValue = targetUnit.fromFeet(baseFeet);

            return new QuantityLength(convertedValue, targetUnit);
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

    // UC5: Static conversion method
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    public static void main(String[] args) {

        System.out.println("Input: convert(1.0, FEET, INCH) -> Output: " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("Input: convert(3.0, YARD, FEET) -> Output: " +
                convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        System.out.println("Input: convert(36.0, INCH, YARD) -> Output: " +
                convert(36.0, LengthUnit.INCH, LengthUnit.YARD));

        System.out.println("Input: convert(1.0, CENTIMETER, INCH) -> Output: " +
                convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH));

        System.out.println("Input: convert(0.0, FEET, INCH) -> Output: " +
                convert(0.0, LengthUnit.FEET, LengthUnit.INCH));
    }
}