package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC4: Added YARDS and CENTIMETERS
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0); // cm -> inch -> feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double convertToFeet(double value) {
            return value * toFeetFactor;
        }
    }

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        public double toFeet() {
            return unit.convertToFeet(value);
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
    }

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Input: Quantity(1.0, YARD) and Quantity(3.0, FEET)");
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength q4 = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, CENTIMETER) and Quantity(0.393701, INCH)");
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");
    }
}