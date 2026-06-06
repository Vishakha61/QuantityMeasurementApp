package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(q1, q2);
    }

    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> converted =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(
                new Quantity<>(12.0, LengthUnit.INCHES),
                converted
        );
    }

    @Test
    void testWeightConversion() {

        Quantity<WeightUnit> q =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> converted =
                q.convertTo(WeightUnit.GRAM);

        assertEquals(
                new Quantity<>(1000.0, WeightUnit.GRAM),
                converted
        );
    }

    @Test
    void testLengthAddition() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(
                new Quantity<>(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testWeightAddition() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                q1.add(q2, WeightUnit.KILOGRAM);

        assertEquals(
                new Quantity<>(2.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testCrossCategoryComparison() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }

    @Test
    void testInvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
void testVolumeEquality() {

    Quantity<VolumeUnit> q1 =
            new Quantity<>(1.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> q2 =
            new Quantity<>(1000.0,
                    VolumeUnit.MILLILITRE);

    assertEquals(q1, q2);
}
        @Test
void testGallonEquality() {

    Quantity<VolumeUnit> q1 =
            new Quantity<>(1.0,
                    VolumeUnit.GALLON);

    Quantity<VolumeUnit> q2 =
            new Quantity<>(3.78541,
                    VolumeUnit.LITRE);

    assertEquals(q1, q2);
}

@Test
void testVolumeConversion() {

    Quantity<VolumeUnit> q =
            new Quantity<>(1.0,
                    VolumeUnit.LITRE);

    Quantity<VolumeUnit> converted =
            q.convertTo(
                    VolumeUnit.MILLILITRE);

    assertEquals(
            new Quantity<>(
                    1000.0,
                    VolumeUnit.MILLILITRE),
            converted
    );
}
@Test
void testGallonToLitreConversion() {

    Quantity<VolumeUnit> q =
            new Quantity<>(1.0,
                    VolumeUnit.GALLON);

    Quantity<VolumeUnit> converted =
            q.convertTo(
                    VolumeUnit.LITRE);
    assertEquals(
            new Quantity<>(
                    3.79,
                    VolumeUnit.LITRE),
            converted
    );
}

@Test
void testVolumeAddition() {

    Quantity<VolumeUnit> q1 =
            new Quantity<>(1.0,
                    VolumeUnit.LITRE);

    Quantity<VolumeUnit> q2 =
            new Quantity<>(1000.0,
                    VolumeUnit.MILLILITRE);

    Quantity<VolumeUnit> result =
            q1.add(
                    q2,
                    VolumeUnit.LITRE);

    assertEquals(
            new Quantity<>(
                    2.0,
                    VolumeUnit.LITRE),
            result
    );
}

@Test
void testVolumeAdditionWithGallon() {

    Quantity<VolumeUnit> q1 =
            new Quantity<>(1.0,
                    VolumeUnit.LITRE);

    Quantity<VolumeUnit> q2 =
            new Quantity<>(1.0,
                    VolumeUnit.GALLON);

    Quantity<VolumeUnit> result =
            q1.add(
                    q2,
                    VolumeUnit.LITRE);

    assertEquals(
            new Quantity<>(
                    4.79,
                    VolumeUnit.LITRE),
            result
    );
}

@Test
void testVolumeVsLength() {

    Quantity<VolumeUnit> volume =
            new Quantity<>(1.0,
                    VolumeUnit.LITRE);

    Quantity<LengthUnit> length =
            new Quantity<>(1.0,
                    LengthUnit.FEET);

    assertNotEquals(volume, length);
}
}