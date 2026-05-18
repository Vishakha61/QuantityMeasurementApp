package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inch() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.INCH);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCH), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.YARDS);

        // 2 feet = 0.666666... yards
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
    public void testAddition_CentimetersPlusInch_TargetCentimeters() {
        QuantityLength q1 = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.CENTIMETERS);

        // 2.54 cm + 1 inch(2.54 cm) = 5.08 cm
        assertEquals(5.08, result.getValue(), 0.0001);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testAddition_WithZero_TargetYards() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.YARDS);

        // 5 feet = 5/3 yards = 1.666666...
        assertEquals(5.0 / 3.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_NegativeValues_TargetInch() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.INCH);

        // (5 - 2) feet = 3 feet = 36 inches
        assertEquals(36.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity_TargetYards() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result1 = QuantityLength.add(q1, q2, LengthUnit.YARDS);
        QuantityLength result2 = QuantityLength.add(q2, q1, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), 0.0001);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    @Test
    public void testAddition_NullTargetUnit_ShouldThrowException() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.add(q1, q2, null);
        });
    }

    @Test
    public void testAddition_NullOperand_ShouldThrowException() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.add(q1, null, LengthUnit.FEET);
        });
    }
}