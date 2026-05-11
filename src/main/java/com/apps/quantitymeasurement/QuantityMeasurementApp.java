package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC1: Feet measurement equality
    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            // Reference check (same object)
            if (this == obj)
                return true;

            // Null check + type check
            if (obj == null || this.getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            // Compare floating values safely
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method (Demo output)
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + f1.equals(f2) + ")");
    }
}

