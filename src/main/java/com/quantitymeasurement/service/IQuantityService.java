package com.quantitymeasurement.service;

import com.quantitymeasurement.model.Quantity;
import com.quantitymeasurement.interfaces.IMeasurable;
public interface IQuantityService {

    <U extends IMeasurable>
    Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit);

    <U extends IMeasurable>
    Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit);

    <U extends IMeasurable>
    double divide(
            Quantity<U> q1,
            Quantity<U> q2);

    <U extends IMeasurable>
    Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit);
}