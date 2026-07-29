package com.app.quantitymeasurement.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityDTO {

    @NotNull(message = "Value cannot be null")
    private Double value;

    @NotBlank(message = "Unit cannot be blank")
    private String unit;

    @NotBlank(message = "Measurement type cannot be blank")
    private String measurementType;

    @AssertTrue(message = "Invalid unit for measurement type")
    public boolean isValidUnit() {

        if (measurementType == null || unit == null) {
            return false;
        }

        switch (measurementType.toUpperCase()) {

            case "LENGTH":
                return unit.equalsIgnoreCase("FEET")
                        || unit.equalsIgnoreCase("INCHES")
                        || unit.equalsIgnoreCase("YARDS")
                        || unit.equalsIgnoreCase("CENTIMETERS");

            case "WEIGHT":
                return unit.equalsIgnoreCase("GRAM")
                        || unit.equalsIgnoreCase("KILOGRAM")
                        || unit.equalsIgnoreCase("POUND");

            case "VOLUME":
                return unit.equalsIgnoreCase("LITRE")
                        || unit.equalsIgnoreCase("MILLILITRE")
                        || unit.equalsIgnoreCase("GALLON");

            case "TEMPERATURE":
                return unit.equalsIgnoreCase("CELSIUS")
                        || unit.equalsIgnoreCase("FAHRENHEIT")
                        || unit.equalsIgnoreCase("KELVIN");

            default:
                return false;
        }
    }
}