package com.app.quantitymeasurement;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.entity.Quantity;
import com.app.quantitymeasurement.service.QuantityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class QuantityMeasurementApp {
        private static final Logger logger =
        LoggerFactory.getLogger(QuantityMeasurementApp.class);

    public static void main(String[] args) {

        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        new QuantityService());

        Quantity<LengthUnit> q1 =
                new Quantity<>(5, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                controller.add(
                        q1,
                        q2,
                        LengthUnit.FEET);

       logger.info("------------------------------");
        logger.info("Addition Result");
        logger.info("------------------------------");
        logger.info("{}", result);

        Quantity<LengthUnit> converted =
                controller.convert(
                        q1,
                        LengthUnit.INCHES);

        logger.info("------------------------------");
        logger.info("Conversion Result");
        logger.info("------------------------------");
        logger.info("{}", converted);

        double division =
                controller.divide(
                        q1,
                        q2);

        logger.info("------------------------------");
        logger.info("Division Result");
        logger.info("------------------------------");
        logger.info("{}", division);
    }
}