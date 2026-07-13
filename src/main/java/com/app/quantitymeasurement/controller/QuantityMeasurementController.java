package com.app.quantitymeasurement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityOperationRequest;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quantity")
@Tag(
        name = "Quantity Measurement API",
        description = "REST APIs for Quantity Measurement Operations"
)
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service
    ) {
        this.service = service;
    }

    @Operation(summary = "Compare two quantities")
    @PostMapping("/compare")
    public ResponseEntity<Boolean> compare(

            @Valid
            @RequestBody
            QuantityOperationRequest request
    ) {

        return ResponseEntity.ok(

                service.compare(

                        request.getQuantity1(),

                        request.getQuantity2()
                )
        );
    }

    @Operation(summary = "Convert a quantity to another unit")
    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(

            @Valid
            @RequestBody
            QuantityOperationRequest request
    ) {

        return ResponseEntity.ok(

                service.convert(

                        request.getQuantity1(),

                        request.getTargetUnit()
                )
        );
    }

    @Operation(summary = "Add two quantities")
    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(

            @Valid
            @RequestBody
            QuantityOperationRequest request
    ) {

        return ResponseEntity.ok(

                service.add(

                        request.getQuantity1(),

                        request.getQuantity2()
                )
        );
    }

    @Operation(summary = "Subtract two quantities")
    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(

            @Valid
            @RequestBody
            QuantityOperationRequest request
    ) {

        return ResponseEntity.ok(

                service.subtract(

                        request.getQuantity1(),

                        request.getQuantity2()
                )
        );
    }

    @Operation(summary = "Divide two quantities")
    @PostMapping("/divide")
    public ResponseEntity<Double> divide(

            @Valid
            @RequestBody
            QuantityOperationRequest request
    ) {

        return ResponseEntity.ok(

                service.divide(

                        request.getQuantity1(),

                        request.getQuantity2()
                )
        );
    }

    @Operation(summary = "Check whether the API is running")
    @GetMapping
    public String home() {

        return "Quantity Measurement API is running...";
    }
}