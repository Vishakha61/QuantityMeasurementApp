package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

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

        public double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        // UC5: Conversion
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseValue = this.toBaseUnit();
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

            return new QuantityLength(convertedValue, targetUnit);
        }

        // UC6: Addition (result in this.unit)
        public QuantityLength add(QuantityLength other) {

            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            double sumInFeet = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = this.unit.convertFromBaseUnit(sumInFeet);

            return new QuantityLength(resultValue, this.unit);
        }

        // UC7: Addition with explicit target unit
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumInFeet = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = targetUnit.convertFromBaseUnit(sumInFeet);

            return new QuantityLength(resultValue, targetUnit);
        }

        // Static UC7 helper
        public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {

            if (a == null || b == null)
                throw new IllegalArgumentException("Lengths cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumInFeet = a.toBaseUnit() + b.toBaseUnit();
            double resultValue = targetUnit.convertFromBaseUnit(sumInFeet);

            return new QuantityLength(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || this.getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Convert FEET to INCHES: " + length1.convertTo(LengthUnit.INCHES));

        System.out.println("UC6 Add Output: " + length1.add(length2));

        System.out.println("UC7 Add Output (YARDS): " + length1.add(length2, LengthUnit.YARDS));

        System.out.println("Equality check: " +
                new QuantityLength(36.0, LengthUnit.INCHES)
                        .equals(new QuantityLength(1.0, LengthUnit.YARDS)));
    }
}