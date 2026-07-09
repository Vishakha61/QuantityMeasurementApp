package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(QuantityMeasurementController.class)
class QuantityMeasurementControllerTest {

    @MockitoBean
    private IQuantityMeasurementService service;

    @Test
    void controllerLoads() {

        assertNotNull(service);
    }
}
