package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.quantity.Quantity;

import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.WeightUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.model.QuantityDTO;
import org.springframework.stereotype.Service;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

import java.util.logging.Logger;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private static final Logger logger =
            Logger.getLogger(
                    QuantityMeasurementServiceImpl.class.getName()
            );

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            QuantityMeasurementRepository repository
    ) {
        this.repository = repository;

        logger.info(
                "QuantityMeasurementService initialized."
        );
    }

    @Override
    public boolean compare(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    ) {

        logger.info(
                "Performing COMPARE operation."
        );

        try {

            Quantity quantity1 =
                    createQuantity(
                            thisQuantityDTO
                    );

            Quantity quantity2 =
                    createQuantity(
                            thatQuantityDTO
                    );

            boolean result =
                    quantity1.equals(
                            quantity2
                    );

            repository.save(
                    new QuantityMeasurementEntity(
                            thisQuantityDTO,
                            thatQuantityDTO,
                            "COMPARE",
                            result
                    )
            );

            return result;

        } catch (RuntimeException exception) {

            logger.severe(
                    "COMPARE failed : "
                            + exception.getMessage()
            );

            throw saveAndCreateException(
                    thisQuantityDTO,
                    thatQuantityDTO,
                    "COMPARE",
                    exception
            );
        }
    }

    @Override
    public QuantityDTO convert(
            QuantityDTO thisQuantityDTO,
            QuantityDTO targetUnitDTO
    ) {

        logger.info(
                "Performing CONVERT operation."
        );

        try {

            Quantity quantity =
                    createQuantity(
                            thisQuantityDTO
                    );

            IMeasurable targetUnit =
                    getUnit(
                            targetUnitDTO
                    );

            Quantity result =
                    quantity.convertTo(
                            targetUnit
                    );

            QuantityDTO dto =
                    toDTO(result);

            repository.save(
                    new QuantityMeasurementEntity(
                            thisQuantityDTO,
                            targetUnitDTO,
                            "CONVERT",
                            dto
                    )
            );

            return dto;

        } catch (RuntimeException exception) {

            logger.severe(
                    "CONVERT failed : "
                            + exception.getMessage()
            );

            throw saveAndCreateException(
                    thisQuantityDTO,
                    targetUnitDTO,
                    "CONVERT",
                    exception
            );
        }
    }

    @Override
    public QuantityDTO add(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    ) {

        logger.info(
                "Performing ADD operation."
        );

        return add(
                thisQuantityDTO,
                thatQuantityDTO,
                thisQuantityDTO
        );
    }

    @Override
    public QuantityDTO add(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO,
            QuantityDTO targetUnitDTO
    ) {

        try {

            Quantity quantity1 =
                    createQuantity(
                            thisQuantityDTO
                    );

            Quantity quantity2 =
                    createQuantity(
                            thatQuantityDTO
                    );

            IMeasurable targetUnit =
                    getUnit(
                            targetUnitDTO
                    );

            Quantity result =
                    quantity1.add(
                            quantity2,
                            targetUnit
                    );

            QuantityDTO dto =
                    toDTO(result);

            repository.save(
                    new QuantityMeasurementEntity(
                            thisQuantityDTO,
                            thatQuantityDTO,
                            "ADD",
                            dto
                    )
            );

            return dto;

        } catch (RuntimeException exception) {

            logger.severe(
                    "ADD failed : "
                            + exception.getMessage()
            );

            throw saveAndCreateException(
                    thisQuantityDTO,
                    thatQuantityDTO,
                    "ADD",
                    exception
            );
        }
    }

    @Override
    public QuantityDTO subtract(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    ) {

        logger.info(
                "Performing SUBTRACT operation."
        );

        return subtract(
                thisQuantityDTO,
                thatQuantityDTO,
                thisQuantityDTO
        );
    }

    @Override
    public QuantityDTO subtract(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO,
            QuantityDTO targetUnitDTO
    ) {

        try {

            Quantity quantity1 =
                    createQuantity(
                            thisQuantityDTO
                    );

            Quantity quantity2 =
                    createQuantity(
                            thatQuantityDTO
                    );

            IMeasurable targetUnit =
                    getUnit(
                            targetUnitDTO
                    );

            Quantity result =
                    quantity1.subtract(
                            quantity2,
                            targetUnit
                    );

            QuantityDTO dto =
                    toDTO(result);

            repository.save(
                    new QuantityMeasurementEntity(
                            thisQuantityDTO,
                            thatQuantityDTO,
                            "SUBTRACT",
                            dto
                    )
            );

            return dto;

        } catch (RuntimeException exception) {

            logger.severe(
                    "SUBTRACT failed : "
                            + exception.getMessage()
            );

            throw saveAndCreateException(
                    thisQuantityDTO,
                    thatQuantityDTO,
                    "SUBTRACT",
                    exception
            );
        }
    }
    @Override
    public double divide(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    ) {

        logger.info(
                "Performing DIVIDE operation."
        );

        try {

            Quantity quantity1 =
                    createQuantity(
                            thisQuantityDTO
                    );

            Quantity quantity2 =
                    createQuantity(
                            thatQuantityDTO
                    );

            double result =
                    quantity1.divide(
                            quantity2
                    );

            repository.save(
                    new QuantityMeasurementEntity(
                            thisQuantityDTO,
                            thatQuantityDTO,
                            "DIVIDE",
                            result
                    )
            );

            return result;

        } catch (RuntimeException exception) {

            logger.severe(
                    "DIVIDE failed : "
                            + exception.getMessage()
            );

            throw saveAndCreateException(
                    thisQuantityDTO,
                    thatQuantityDTO,
                    "DIVIDE",
                    exception
            );
        }
    }

    private Quantity createQuantity(
            QuantityDTO dto
    ) {

        return new Quantity(
                dto.getValue(),
                getUnit(dto)
        );
    }

    private IMeasurable getUnit(
            QuantityDTO dto
    ) {

        switch (
                dto.getMeasurementType()
                        .toUpperCase()
        ) {

            case "LENGTH":
                return LengthUnit.valueOf(
                        dto.getUnit().toUpperCase()
                );

            case "WEIGHT":
                return WeightUnit.valueOf(
                        dto.getUnit().toUpperCase()
                );

            case "VOLUME":
                return VolumeUnit.valueOf(
                        dto.getUnit().toUpperCase()
                );

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(
                        dto.getUnit().toUpperCase()
                );

            default:
                throw new IllegalArgumentException(
                        "Invalid measurement type"
                );
        }
    }

    private String getMeasurementType(
            IMeasurable unit
    ) {

        if (unit instanceof LengthUnit) {
            return "LENGTH";
        }

        if (unit instanceof WeightUnit) {
            return "WEIGHT";
        }

        if (unit instanceof VolumeUnit) {
            return "VOLUME";
        }

        if (unit instanceof TemperatureUnit) {
            return "TEMPERATURE";
        }

        throw new IllegalArgumentException(
                "Unknown measurement type"
        );
    }

    private QuantityDTO toDTO(
            Quantity quantity
    ) {

        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().getUnitName(),
                getMeasurementType(
                        quantity.getUnit()
                )
        );
    }

    private QuantityMeasurementException
    saveAndCreateException(

            QuantityDTO thisQuantityDTO,

            QuantityDTO thatQuantityDTO,

            String operation,

            RuntimeException exception
    ) {

        logger.severe(
                operation
                        + " operation failed : "
                        + exception.getMessage()
        );

        repository.save(
                new QuantityMeasurementEntity(
                        thisQuantityDTO,
                        thatQuantityDTO,
                        operation,
                        exception.getMessage(),
                        true
                )
        );

        return new QuantityMeasurementException(
                exception.getMessage(),
                exception
        );
    }
}

