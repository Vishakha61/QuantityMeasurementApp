package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    @Test
    public void testAddition_FeetPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_FeetPlusInch() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_InchPlusFeet() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCH), result);
    }
}