package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.INCHES);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_YardsPlusFeet_TargetYards() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.YARDS);

        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), result);
    }

    @Test
    public void testConvertTo_Inches() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q1.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testEquality_FeetAndInches() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    public void testAddition_WithZero_TargetFeet() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = q1.add(q2, LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_NegativeValues_TargetInches() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity_TargetYards() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result1 = q1.add(q2, LengthUnit.YARDS);
        QuantityLength result2 = q2.add(q1, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), 0.0001);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    @Test
    public void testNullTargetUnit_ShouldThrowException() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            q1.add(q2, null);
        });
    }

    @Test
    public void testInvalidValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(Double.NaN, LengthUnit.FEET);
        });
    }

    @Test
    public void testLengthUnitConvertToBaseUnit_InchesToFeet() {
        double feetValue = LengthUnit.INCHES.convertToBaseUnit(12.0);

        assertEquals(1.0, feetValue, 0.0001);
    }

    @Test
    public void testLengthUnitConvertFromBaseUnit_FeetToYards() {
        double yardsValue = LengthUnit.YARDS.convertFromBaseUnit(3.0);

        assertEquals(1.0, yardsValue, 0.0001);
    }
}