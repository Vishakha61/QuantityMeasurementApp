package com.quantitymeasurement.service;

import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.interfaces.IMeasurable;
import com.quantitymeasurement.repository.IQuantityRepository;
import com.quantitymeasurement.repository.QuantityCacheRepository;

public class QuantityService
        implements IQuantityService {

    private final IQuantityRepository repository =
            new QuantityCacheRepository();

    @Override
    public <U extends IMeasurable>
    Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        repository.save();

        return q1.add(
                q2,
                targetUnit
        );
    }

    @Override
    public <U extends IMeasurable>
    Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        repository.save();

        return q1.subtract(
                q2,
                targetUnit
        );
    }

    @Override
    public <U extends IMeasurable>
    double divide(
            Quantity<U> q1,
            Quantity<U> q2
    ) {

        repository.save();

        return q1.divide(q2);
    }

    @Override
    public <U extends IMeasurable>
    Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit
    ) {

        repository.save();

        return quantity.convertTo(
                targetUnit
        );
    }
}