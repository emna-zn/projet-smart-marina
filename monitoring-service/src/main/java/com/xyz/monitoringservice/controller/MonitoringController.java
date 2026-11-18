package com.xyz.monitoringservice.controller;

import com.xyz.monitoringservice.consumer.TelemetryConsumer;
import com.xyz.monitoringservice.dto.TelemetryEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monitoring/pedestals")
public class MonitoringController {

    private final TelemetryConsumer consumer;

    public MonitoringController(TelemetryConsumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping("/{id}/latest")
    public ResponseEntity<TelemetryEvent> getLatest(@PathVariable String id) {

        TelemetryEvent event = consumer.getLatestTelemetry(id);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(event);
    }
}