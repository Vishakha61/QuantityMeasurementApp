package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC3: LengthUnit Enum (conversion factor to base unit FEET)
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double convertToFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // UC3: Generic QuantityLength class (replaces Feet and Inch classes)
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

    // Main method demo
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, \"feet\") and Quantity(12.0, \"inches\")");
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, \"inch\") and Quantity(1.0, \"inch\")");
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");
    }
}