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

        public static <U extends IMeasurable>
void demonstrateSubtraction(
        Quantity<U> q1,
        Quantity<U> q2,
        U targetUnit
) {

    System.out.println(
            q1 + " - " + q2 +
                    " -> " +
                    q1.subtract(
                            q2,
                            targetUnit
                    )
    );
}
        public static <U extends IMeasurable>
void demonstrateDivision(
        Quantity<U> q1,
        Quantity<U> q2
) {

    System.out.println(
            q1 + " / " + q2 +
                    " -> " +
                    q1.divide(q2)
    );
}
    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);


        Quantity<VolumeUnit> volume1 =
        new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
        new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
        new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("\n===== VOLUME =====");

        demonstrateEquality(
        volume1,
        volume2
        );

        demonstrateConversion(
        volume1,
        VolumeUnit.MILLILITRE
        );

        demonstrateConversion(
        volume3,
        VolumeUnit.LITRE
        );

        demonstrateAddition(
        volume1,
        volume2,
        VolumeUnit.LITRE
        );

        demonstrateAddition(
        volume1,
        volume3,
        VolumeUnit.MILLILITRE
        );
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

        System.out.println(
        "\n===== SUBTRACTION ====="
);

demonstrateSubtraction(
        length1,
        length2,
        LengthUnit.FEET
);

demonstrateSubtraction(
        weight1,
        weight2,
        WeightUnit.KILOGRAM
);

demonstrateSubtraction(
        volume1,
        volume2,
        VolumeUnit.LITRE
);

System.out.println(
        "\n===== DIVISION ====="
);

demonstrateDivision(
        length1,
        length2
);

demonstrateDivision(
        weight1,
        weight2
);

demonstrateDivision(
        volume1,
        volume2
);

System.out.println(
        "\n===== TEMPERATURE ====="
);

Quantity<TemperatureUnit> temp1 =
        new Quantity<>(
                0.0,
                TemperatureUnit.CELSIUS
        );

Quantity<TemperatureUnit> temp2 =
        new Quantity<>(
                32.0,
                TemperatureUnit.FAHRENHEIT
        );

Quantity<TemperatureUnit> temp3 =
        new Quantity<>(
                273.15,
                TemperatureUnit.KELVIN
        );

demonstrateEquality(
        temp1,
        temp2
);

demonstrateEquality(
        temp1,
        temp3
);

demonstrateConversion(
        temp1,
        TemperatureUnit.FAHRENHEIT
);

demonstrateConversion(
        temp2,
        TemperatureUnit.CELSIUS
);

demonstrateConversion(
        temp3,
        TemperatureUnit.CELSIUS
);

try {

    demonstrateAddition(
            temp1,
            temp2,
            TemperatureUnit.CELSIUS
    );

} catch (
        UnsupportedOperationException e
) {

    System.out.println(
            "Temperature Addition Error: "
                    + e.getMessage()
    );
}

try {

    demonstrateSubtraction(
            temp1,
            temp2,
            TemperatureUnit.CELSIUS
    );

} catch (
        UnsupportedOperationException e
) {

    System.out.println(
            "Temperature Subtraction Error: "
                    + e.getMessage()
    );
}

try {

    demonstrateDivision(
            temp1,
            temp2
    );

} catch (
        UnsupportedOperationException e
) {

    System.out.println(
            "Temperature Division Error: "
                    + e.getMessage()
    );
}
    }
}