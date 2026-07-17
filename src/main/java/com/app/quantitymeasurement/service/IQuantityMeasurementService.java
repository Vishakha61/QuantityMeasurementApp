package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {

    boolean compare(
            QuantityInputDTO inputDTO
    );

    QuantityDTO convert(
            QuantityInputDTO inputDTO
    );

    QuantityDTO add(
            QuantityInputDTO inputDTO
    );

    QuantityDTO subtract(
            QuantityInputDTO inputDTO
    );

    double divide(
            QuantityInputDTO inputDTO
    );

    List<QuantityMeasurementDTO> getHistory();

    List<QuantityMeasurementDTO> getHistoryByOperation(
            String operation
    );

    List<QuantityMeasurementDTO> getHistoryByMeasurementType(
            String measurementType
    );

    List<QuantityMeasurementDTO> getErroredOperations();

    long getOperationCount(
            String operation
    );
}