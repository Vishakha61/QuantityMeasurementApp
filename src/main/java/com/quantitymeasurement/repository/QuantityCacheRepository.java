package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.interfaces.IMeasurable;
public class QuantityCacheRepository
        implements IQuantityRepository {

    @Override
    public void save() {

        System.out.println(
                "Data stored in cache repository");
    }
}