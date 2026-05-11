package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_YardToYard_SameValue() {
        QuantityLength q1 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.YARD);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToInch_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimeterToCentimeter_SameValue() {
        QuantityLength q1 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.CENTIMETER);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimeterToInch_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength q2 = new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimeterToFeet_NotEquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }
}