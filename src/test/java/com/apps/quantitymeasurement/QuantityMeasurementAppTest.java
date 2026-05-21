package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityWeight;

public class QuantityMeasurementAppTest {

    // ==================== WEIGHT EQUALITY ====================

    @Test
    public void testEquality_KilogramToGram() {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_KilogramToPound() {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(2.20462, WeightUnit.POUND);

        assertEquals(w1.toBaseUnit(),
                w2.toBaseUnit(),
                0.001);
    }

    @Test
    public void testWeightVsLength_Incompatible() {

        QuantityWeight weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityLength length =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }

    // ==================== CONVERSION ====================

    @Test
    public void testConversion_KilogramToGram() {

        QuantityWeight weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result =
                weight.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0,
                result.getValue(),
                0.0001);
    }

    @Test
    public void testConversion_PoundToKilogram() {

        QuantityWeight weight =
                new QuantityWeight(2.20462, WeightUnit.POUND);

        QuantityWeight result =
                weight.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0,
                result.getValue(),
                0.01);
    }

    // ==================== ADDITION ====================

    @Test
    public void testAddition_KilogramPlusGram() {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(2.0,
                result.getValue(),
                0.0001);

        assertEquals(WeightUnit.KILOGRAM,
                result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram() {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result =
                w1.add(w2, WeightUnit.GRAM);

        assertEquals(2000.0,
                result.getValue(),
                0.0001);

        assertEquals(WeightUnit.GRAM,
                result.getUnit());
    }

    @Test
    public void testAddition_WithNegativeValues() {

        QuantityWeight w1 =
                new QuantityWeight(5.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(-2000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(3.0,
                result.getValue(),
                0.0001);
    }

    @Test
    public void testNullUnit_ShouldThrowException() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }

    @Test
    public void testInvalidValue_ShouldThrowException() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN,
                        WeightUnit.KILOGRAM));
    }
}