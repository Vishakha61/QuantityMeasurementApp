package com.app.quantitymeasurement.entity;


import jakarta.persistence.*;
import com.app.quantitymeasurement.model.QuantityDTO;

@Entity
@Table(name = "quantity_measurement_history")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_value")
    private Double firstValue;

    @Column(name = "first_unit")
    private String firstUnit;

    @Column(name = "first_measurement_type")
    private String firstMeasurementType;

    @Column(name = "second_value")
    private Double secondValue;

    @Column(name = "second_unit")
    private String secondUnit;

    @Column(name = "second_measurement_type")
    private String secondMeasurementType;

    private String operation;

    private String result;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "is_error")
    private boolean error;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(
            QuantityDTO first,
            QuantityDTO second,
            String operation,
            Object result
    ) {

        this.firstValue = first.getValue();
        this.firstUnit = first.getUnit();
        this.firstMeasurementType = first.getMeasurementType();

        if (second != null) {

            this.secondValue = second.getValue();
            this.secondUnit = second.getUnit();
            this.secondMeasurementType =
                    second.getMeasurementType();
        }

        this.operation = operation;

        this.result =
                String.valueOf(result);

        this.error = false;
    }

    public QuantityMeasurementEntity(
            QuantityDTO first,
            QuantityDTO second,
            String operation,
            String errorMessage,
            boolean error
    ) {

        this(first, second, operation, "");

        this.result = null;

        this.errorMessage = errorMessage;

        this.error = error;
    }

    public Integer getId() {
        return id;
    }

    public Double getFirstValue() {
        return firstValue;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public String getFirstMeasurementType() {
        return firstMeasurementType;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public String getSecondMeasurementType() {
        return secondMeasurementType;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isError() {
        return error;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstValue(Double firstValue) {
        this.firstValue = firstValue;
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit;
    }

    public void setFirstMeasurementType(String firstMeasurementType) {
        this.firstMeasurementType = firstMeasurementType;
    }

    public void setSecondValue(Double secondValue) {
        this.secondValue = secondValue;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
    }

    public void setSecondMeasurementType(String secondMeasurementType) {
        this.secondMeasurementType = secondMeasurementType;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}