package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ========================= LENGTH =========================

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

        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseValue = this.toBaseUnit();
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

            return new QuantityLength(convertedValue, targetUnit);
        }

        public QuantityLength add(QuantityLength other) {

            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            double sumInFeet = this.toBaseUnit() + other.toBaseUnit();

            double resultValue = this.unit.convertFromBaseUnit(sumInFeet);

            return new QuantityLength(resultValue, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumInFeet = this.toBaseUnit() + other.toBaseUnit();

            double resultValue = targetUnit.convertFromBaseUnit(sumInFeet);

            return new QuantityLength(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ========================= WEIGHT =========================

    public static class QuantityWeight {

        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {

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

        public WeightUnit getUnit() {
            return unit;
        }

        public double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseValue = this.toBaseUnit();

            double convertedValue =
                    targetUnit.convertFromBaseUnit(baseValue);

            return new QuantityWeight(convertedValue, targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {

            if (other == null)
                throw new IllegalArgumentException("Other weight cannot be null");

            double sumInKilograms =
                    this.toBaseUnit() + other.toBaseUnit();

            double resultValue =
                    this.unit.convertFromBaseUnit(sumInKilograms);

            return new QuantityWeight(resultValue, this.unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other weight cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumInKilograms =
                    this.toBaseUnit() + other.toBaseUnit();

            double resultValue =
                    targetUnit.convertFromBaseUnit(sumInKilograms);

            return new QuantityWeight(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityWeight other = (QuantityWeight) obj;

            return Double.compare(this.toBaseUnit(),
                    other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ========================= MAIN =========================

    public static void main(String[] args) {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Equality: " + w1.equals(w2));

        System.out.println("Convert KG to POUND: "
                + w1.convertTo(WeightUnit.POUND));

        System.out.println("Addition: "
                + w1.add(w2));

        System.out.println("Addition with target unit: "
                + w1.add(w2, WeightUnit.GRAM));

        QuantityLength l1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        System.out.println("Weight vs Length equality: "
                + w1.equals(l1));
    }
}