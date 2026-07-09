package com.app.quantitymeasurement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class QuantityDTO
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Positive(message = "Value must be greater than zero")
    private double value;

    @NotBlank(message = "Unit cannot be empty")
    private String unit;

    @NotBlank(message = "Measurement type cannot be empty")
    private String measurementType;

    private boolean hasError;

    private String errorMessage;

    public QuantityDTO() {
    }

    public QuantityDTO(
            double value,
            String unit,
            String measurementType
    ) {

        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public QuantityDTO(
            String errorMessage
    ) {

        this.hasError = true;
        this.errorMessage = errorMessage;
    }

    public double getValue() {

        return value;
    }

    public void setValue(
            double value
    ) {

        this.value = value;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(
            String unit
    ) {

        this.unit = unit;
    }

    public String getMeasurementType() {

        return measurementType;
    }

    public void setMeasurementType(
            String measurementType
    ) {

        this.measurementType = measurementType;
    }

    public boolean hasError() {

        return hasError;
    }

    public void setHasError(
            boolean hasError
    ) {

        this.hasError = hasError;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public void setErrorMessage(
            String errorMessage
    ) {

        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {

        if (hasError) {

            return "Error : " + errorMessage;
        }

        return value + " " + unit;
    }
}