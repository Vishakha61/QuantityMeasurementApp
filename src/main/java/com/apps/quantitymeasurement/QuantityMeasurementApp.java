package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC1 + UC2: Feet class
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double toInches() {
            return value * 12;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || this.getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // UC2: Inch class
    public static class Inch {
        private final double value;

        public Inch(double value) {
            this.value = value;
        }

        public double toInches() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || this.getClass() != obj.getClass())
                return false;

            Inch other = (Inch) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // UC2 comparison method (Feet and Inch)
    public static boolean compare(Feet feet, Inch inch) {
        return Double.compare(feet.toInches(), inch.toInches()) == 0;
    }

    public static void main(String[] args) {

        Feet feet = new Feet(1.0);
        Inch inch = new Inch(12.0);

        System.out.println("Input: 1.0 ft and 12.0 inch");
        System.out.println("Output: Equal (" + compare(feet, inch) + ")");
    }
}
