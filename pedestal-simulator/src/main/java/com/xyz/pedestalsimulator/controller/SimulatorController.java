
package com.xyz.pedestalsimulator.controller;

import com.xyz.pedestalsimulator.dto.TelemetryEvent;
import com.xyz.pedestalsimulator.producer.TelemetryProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/simulator")
public class SimulatorController {

    private final TelemetryProducer producer;

    public SimulatorController(TelemetryProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public ResponseEntity<TelemetryEvent> sendTelemetry(
            @RequestBody TelemetryRequest request) {

        TelemetryEvent event = new TelemetryEvent(
                UUID.randomUUID().toString(),
                request.pedestalId(),
                Instant.now(),
                request.electricityKwh(),
                request.waterM3(),
                request.status()
        );

        producer.send(event);

        return ResponseEntity.ok(event);
    }

    public record TelemetryRequest(
            String pedestalId,
            double electricityKwh,
            double waterM3,
            String status
    ) {}
}
