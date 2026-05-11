package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inch;

public class QuantityMeasurementAppTest {

    // UC1 test
    @Test
    public void testFeetEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    // UC1 test
    @Test
    public void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    // UC2 test
    @Test
    public void testFeetAndInchEquality() {
        Feet feet = new Feet(1.0);
        Inch inch = new Inch(12.0);

        assertTrue(QuantityMeasurementApp.compare(feet, inch));
    }

    // UC2 test
    @Test
    public void testFeetAndInchNotEqual() {
        Feet feet = new Feet(1.0);
        Inch inch = new Inch(13.0);

        assertFalse(QuantityMeasurementApp.compare(feet, inch));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        Feet feet = new Feet(1.0);

        assertFalse(feet.equals(null));
    }

    @Test
    public void testFeetEquality_NonNumericInput() {
        Feet feet = new Feet(1.0);

        assertFalse(feet.equals("hello"));
    }

    @Test
    public void testFeetEquality_SameReference() {
        Feet feet = new Feet(1.0);

        assertTrue(feet.equals(feet));
    }
}