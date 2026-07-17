package com.app.quantitymeasurement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    private IMeasurable getUnit(
            String measurementType,
            String unit
    ) {

        return switch (measurementType.toUpperCase()) {

            case "LENGTH" ->
                    LengthUnit.valueOf(unit);

            case "WEIGHT" ->
                    WeightUnit.valueOf(unit);

            case "VOLUME" ->
                    VolumeUnit.valueOf(unit);

            case "TEMPERATURE" ->
                    TemperatureUnit.valueOf(unit);

            default ->
                    throw new IllegalArgumentException(
                            "Invalid measurement type"
                    );
        };
    }

    @SuppressWarnings("unchecked")
    private Quantity<IMeasurable> createQuantity(
            QuantityDTO dto
    ) {

        return new Quantity<>(

                dto.getValue(),

                getUnit(
                        dto.getMeasurementType(),
                        dto.getUnit()
                )
        );
    }

    private QuantityDTO convertToDTO(
            Quantity<IMeasurable> quantity
    ) {

        return new QuantityDTO(

                quantity.getValue(),

                quantity.getUnit().getUnitName(),

                quantity.getUnit().getMeasurementType()
        );
    }

    private void saveSuccess(

            QuantityDTO first,

            QuantityDTO second,

            String operation,

            QuantityDTO result
    ) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setThisValue(
                first.getValue()
        );

        entity.setThisUnit(
                first.getUnit()
        );

        entity.setThisMeasurementType(
                first.getMeasurementType()
        );

        entity.setThatValue(
                second.getValue()
        );

        entity.setThatUnit(
                second.getUnit()
        );

        entity.setThatMeasurementType(
                second.getMeasurementType()
        );

        entity.setOperation(
                operation
        );

        entity.setResultValue(
                result.getValue()
        );

        entity.setResultUnit(
                result.getUnit()
        );

        entity.setResultMeasurementType(
                result.getMeasurementType()
        );

        entity.setResultString(
                result.toString()
        );

        entity.setError(false);

        entity.setErrorMessage(null);

        repository.save(entity);
    }

    private void saveError(

            QuantityDTO first,

            QuantityDTO second,

            String operation,

            Exception exception
    ) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setThisValue(
                first.getValue()
        );

        entity.setThisUnit(
                first.getUnit()
        );

        entity.setThisMeasurementType(
                first.getMeasurementType()
        );

        entity.setThatValue(
                second.getValue()
        );

        entity.setThatUnit(
                second.getUnit()
        );

        entity.setThatMeasurementType(
                second.getMeasurementType()
        );

        entity.setOperation(
                operation
        );

        entity.setResultString(null);

        entity.setError(true);

        entity.setErrorMessage(
                exception.getMessage()
        );

        repository.save(entity);
    }
    @Override
    public boolean compare(
            QuantityInputDTO inputDTO
    ) {

        Quantity<IMeasurable> first =
                createQuantity(
                        inputDTO.getThisQuantity()
                );

        Quantity<IMeasurable> second =
                createQuantity(
                        inputDTO.getThatQuantity()
                );

        boolean result =
                first.compare(second);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setThisValue(
                inputDTO.getThisQuantity().getValue()
        );

        entity.setThisUnit(
                inputDTO.getThisQuantity().getUnit()
        );

        entity.setThisMeasurementType(
                inputDTO.getThisQuantity().getMeasurementType()
        );

        entity.setThatValue(
                inputDTO.getThatQuantity().getValue()
        );

        entity.setThatUnit(
                inputDTO.getThatQuantity().getUnit()
        );

        entity.setThatMeasurementType(
                inputDTO.getThatQuantity().getMeasurementType()
        );

        entity.setOperation("COMPARE");

        entity.setResultString(
                String.valueOf(result)
        );

        entity.setError(false);

        entity.setErrorMessage(null);

        repository.save(entity);

        return result;
    }

    @Override
    public QuantityDTO convert(
            QuantityInputDTO inputDTO
    ) {

        try {

            Quantity<IMeasurable> quantity =
                    createQuantity(
                            inputDTO.getThisQuantity()
                    );

            IMeasurable targetUnit =
                    getUnit(
                            inputDTO.getThisQuantity()
                                    .getMeasurementType(),
                            inputDTO.getTargetUnit()
                    );

            Quantity<IMeasurable> converted =
                    quantity.convertTo(targetUnit);

            QuantityDTO result =
                    convertToDTO(converted);

            saveSuccess(
                    inputDTO.getThisQuantity(),
                    inputDTO.getThatQuantity(),
                    "CONVERT",
                    result
            );

            return result;

        } catch (Exception exception) {

            saveError(
                    inputDTO.getThisQuantity(),
                    inputDTO.getThatQuantity(),
                    "CONVERT",
                    exception
            );

            throw exception;
        }
    }

    @Override
    public QuantityDTO add(
            QuantityInputDTO inputDTO
    ) {

        try {

            Quantity<IMeasurable> first =
                    createQuantity(
                            inputDTO.getThisQuantity()
                    );

            Quantity<IMeasurable> second =
                    createQuantity(
                            inputDTO.getThatQuantity()
                    );

            Quantity<IMeasurable> resultQuantity =
                    first.add(second);

            QuantityDTO result =
                    convertToDTO(
                            resultQuantity
                    );

            saveSuccess(
                    inputDTO.getThisQuantity(),
                    inputDTO.getThatQuantity(),
                    "ADD",
                    result
            );

            return result;

        } catch (Exception exception) {

            saveError(
                    inputDTO.getThisQuantity(),
                    inputDTO.getThatQuantity(),
                    "ADD",
                    exception
            );

            throw exception;
        }
    }
    @Override
    public QuantityDTO subtract(
            QuantityInputDTO inputDTO
    ) {

        try {

            Quantity<IMeasurable> first =
                    createQuantity(
                            inputDTO.getThisQuantity()
                    );

            Quantity<IMeasurable> second =
                    createQuantity(
                            inputDTO.getThatQuantity()
                    );

            Quantity<IMeasurable> resultQuantity =
                    first.subtract(second);

            QuantityDTO result =
                    convertToDTO(
                            resultQuantity
                    );

            saveSuccess(
                    inputDTO.getThisQuantity(),
                    inputDTO.getThatQuantity(),
                    "SUBTRACT",
                    result
            );

            return result;

        } catch (Exception exception) {

            saveError(
                    inputDTO.getThisQuantity(),
                    inputDTO.getThatQuantity(),
                    "SUBTRACT",
                    exception
            );

            throw exception;
        }
    }

    @Override
    public double divide(
            QuantityInputDTO inputDTO
    ) {

        try {

            Quantity<IMeasurable> first =
                    createQuantity(
                            inputDTO.getThisQuantity()
                    );

            Quantity<IMeasurable> second =
                    createQuantity(
                            inputDTO.getThatQuantity()
                    );

            double result =
                    first.divide(second);

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity();

            entity.setThisValue(
                    inputDTO.getThisQuantity().getValue()
            );

            entity.setThisUnit(
                    inputDTO.getThisQuantity().getUnit()
            );

            entity.setThisMeasurementType(
                    inputDTO.getThisQuantity().getMeasurementType()
            );

            entity.setThatValue(
                    inputDTO.getThatQuantity().getValue()
            );

            entity.setThatUnit(
                    inputDTO.getThatQuantity().getUnit()
            );

            entity.setThatMeasurementType(
                    inputDTO.getThatQuantity().getMeasurementType()
            );

            entity.setOperation("DIVIDE");

            entity.setResultString(
                    String.valueOf(result)
            );

            entity.setError(false);

            entity.setErrorMessage(null);

            repository.save(entity);

            return result;

        } catch (Exception exception) {

            saveError(
                    inputDTO.getThisQuantity(),
                    inputDTO.getThatQuantity(),
                    "DIVIDE",
                    exception
            );

            throw exception;
        }
    }
    @Override
    public List<QuantityMeasurementDTO> getHistory() {

        return QuantityMeasurementDTO.fromEntityList(
                repository.findAll()
        );
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(
            String operation
    ) {

        return QuantityMeasurementDTO.fromEntityList(
                repository.findByOperation(operation)
        );
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(
            String measurementType
    ) {

        return QuantityMeasurementDTO.fromEntityList(
                repository.findByThisMeasurementType(
                        measurementType
                )
        );
    }

    @Override
    public List<QuantityMeasurementDTO> getErroredOperations() {

        return QuantityMeasurementDTO.fromEntityList(
                repository.findByIsErrorTrue()
        );
    }

    @Override
    public long getOperationCount(
            String operation
    ) {

        return repository.countByOperationAndIsErrorFalse(
                operation
        );
    }
}