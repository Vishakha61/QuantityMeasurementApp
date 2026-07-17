package com.app.quantitymeasurement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementDTO {

    private Long id;

    private QuantityDTO firstQuantity;

    private QuantityDTO secondQuantity;

    private String operation;

    private Object result;

    private String errorMessage;

    private boolean error;

    public static QuantityMeasurementDTO fromEntity(
            QuantityMeasurementEntity entity
    ) {

        QuantityDTO first = new QuantityDTO(

                entity.getThisValue(),

                entity.getThisUnit(),

                entity.getThisMeasurementType()
        );

        QuantityDTO second = new QuantityDTO(

                entity.getThatValue(),

                entity.getThatUnit(),

                entity.getThatMeasurementType()
        );

        Object result;

        if (entity.getResultValue() != null) {

            result = new QuantityDTO(

                    entity.getResultValue(),

                    entity.getResultUnit(),

                    entity.getResultMeasurementType()
            );

        } else {

            result = entity.getResultString();
        }

        return new QuantityMeasurementDTO(

                entity.getId(),

                first,

                second,

                entity.getOperation(),

                result,

                entity.getErrorMessage(),

                entity.isError()
        );
    }

    public static List<QuantityMeasurementDTO> fromEntityList(

            List<QuantityMeasurementEntity> entities
    ) {

        return entities

                .stream()

                .map(QuantityMeasurementDTO::fromEntity)

                .collect(Collectors.toList());
    }
}