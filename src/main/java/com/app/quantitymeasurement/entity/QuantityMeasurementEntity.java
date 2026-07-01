package com.app.quantitymeasurement.entity;

import java.time.LocalDateTime;

public class QuantityMeasurementEntity {

    private Long id;

    private String measurementType;

    private String operation;

    private double thisValue;

    private String thisUnit;

    private double thatValue;

    private String thatUnit;

    private double resultValue;

    private String resultUnit;

    private String resultString;

    private boolean isError;

    private String errorMessage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(
            String measurementType,
            String operation,
            double thisValue,
            String thisUnit,
            double thatValue,
            String thatUnit,
            double resultValue,
            String resultUnit,
            String resultString,
            boolean isError,
            String errorMessage
    ) {
        this.measurementType = measurementType;
        this.operation = operation;
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.resultString = resultString;
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getThisValue() {
        return thisValue;
    }

    public void setThisValue(double thisValue) {
        this.thisValue = thisValue;
    }

    public String getThisUnit() {
        return thisUnit;
    }

    public void setThisUnit(String thisUnit) {
        this.thisUnit = thisUnit;
    }

    public double getThatValue() {
        return thatValue;
    }

    public void setThatValue(double thatValue) {
        this.thatValue = thatValue;
    }

    public String getThatUnit() {
        return thatUnit;
    }

    public void setThatUnit(String thatUnit) {
        this.thatUnit = thatUnit;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "id=" + id +
                ", measurementType='" + measurementType + '\'' +
                ", operation='" + operation + '\'' +
                ", thisValue=" + thisValue +
                ", thisUnit='" + thisUnit + '\'' +
                ", thatValue=" + thatValue +
                ", thatUnit='" + thatUnit + '\'' +
                ", resultValue=" + resultValue +
                ", resultUnit='" + resultUnit + '\'' +
                ", resultString='" + resultString + '\'' +
                ", isError=" + isError +
                ", errorMessage='" + errorMessage + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}