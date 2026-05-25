package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2
    ) {

        System.out.println(
                q1 + " equals " + q2 +
                        " -> " + q1.equals(q2)
        );
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit
    ) {

        System.out.println(
                quantity + " converted to " +
                        targetUnit.getUnitName() +
                        " -> " +
                        quantity.convertTo(targetUnit)
        );
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        System.out.println(
                q1 + " + " + q2 +
                        " -> " +
                        q1.add(q2, targetUnit)
        );
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);

        demonstrateConversion(
                length1,
                LengthUnit.INCHES
        );

        demonstrateAddition(
                length1,
                length2,
                LengthUnit.FEET
        );

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);

        demonstrateConversion(
                weight1,
                WeightUnit.GRAM
        );

        demonstrateAddition(
                weight1,
                weight2,
                WeightUnit.KILOGRAM
        );
    }
}