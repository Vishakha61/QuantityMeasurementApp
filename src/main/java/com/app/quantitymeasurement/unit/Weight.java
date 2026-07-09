package com.app.quantitymeasurement.unit;

public class Weight implements Comparable<Weight>{

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 0.01;

    public Weight(double value, WeightUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double valueInKg = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueInKg);

        return new Weight(convertedValue, targetUnit);
    }

    public Weight add(Weight other) {
        return add(other, this.unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double thisKg = this.unit.convertToBaseUnit(this.value);
        double otherKg = other.unit.convertToBaseUnit(other.value);

        double sumKg = thisKg + otherKg;
        double result = targetUnit.convertFromBaseUnit(sumKg);

        return new Weight(result, targetUnit);
    }

    @Override
    public int compareTo(Weight other) {

        if (other == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        double thisKg = this.unit.convertToBaseUnit(this.value);
        double otherKg = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisKg, otherKg);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Weight other = (Weight) obj;

        double thisKg = this.unit.convertToBaseUnit(this.value);
        double otherKg = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisKg - otherKg) < EPSILON;
    }

//    @Override
//    public int hashCode() {
//        double kgValue = unit.convertToBaseUnit(value);
//        return Double.valueOf(Math.round(kgValue / EPSILON)).hashCode();
//    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}