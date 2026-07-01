package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.Quantity;
import com.app.quantitymeasurement.interfaces.IMeasurable;
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