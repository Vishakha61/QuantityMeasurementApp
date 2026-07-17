package com.app.quantitymeasurement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quantity")
@RequiredArgsConstructor
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    @PostMapping("/compare")
    public ResponseEntity<Boolean> compare(
            @Valid
            @RequestBody
            QuantityInputDTO inputDTO
    ) {

        return ResponseEntity.ok(
                service.compare(inputDTO)
        );
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(
            @Valid
            @RequestBody
            QuantityInputDTO inputDTO
    ) {

        return ResponseEntity.ok(
                service.convert(inputDTO)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(
            @Valid
            @RequestBody
            QuantityInputDTO inputDTO
    ) {

        return ResponseEntity.ok(
                service.add(inputDTO)
        );
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(
            @Valid
            @RequestBody
            QuantityInputDTO inputDTO
    ) {

        return ResponseEntity.ok(
                service.subtract(inputDTO)
        );
    }

    @PostMapping("/divide")
    public ResponseEntity<Double> divide(
            @Valid
            @RequestBody
            QuantityInputDTO inputDTO
    ) {

        return ResponseEntity.ok(
                service.divide(inputDTO)
        );
    }

    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistory() {

        return ResponseEntity.ok(
                service.getHistory()
        );
    }

    @GetMapping("/history/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(
            @PathVariable String operation
    ) {

        return ResponseEntity.ok(
                service.getHistoryByOperation(operation)
        );
    }

    @GetMapping("/measurement/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByMeasurementType(
            @PathVariable String measurementType
    ) {

        return ResponseEntity.ok(
                service.getHistoryByMeasurementType(measurementType)
        );
    }

    @GetMapping("/errors")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErroredOperations() {

        return ResponseEntity.ok(
                service.getErroredOperations()
        );
    }

    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getOperationCount(
            @PathVariable String operation
    ) {

        return ResponseEntity.ok(
                service.getOperationCount(operation)
        );
    }
}