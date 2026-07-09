package com.app.quantitymeasurement.model;

public class QuantityOperationRequest {

    private QuantityDTO quantity1;

    private QuantityDTO quantity2;

    private QuantityDTO targetUnit;

    public QuantityOperationRequest() {
    }

    public QuantityDTO getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(
            QuantityDTO quantity1
    ) {
        this.quantity1 = quantity1;
    }

    public QuantityDTO getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(
            QuantityDTO quantity2
    ) {
        this.quantity2 = quantity2;
    }

    public QuantityDTO getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(
            QuantityDTO targetUnit
    ) {
        this.targetUnit = targetUnit;
    }
}