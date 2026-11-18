
package com.xyz.pedestalsimulator.simulator;

import com.xyz.pedestalsimulator.dto.TelemetryEvent;
import com.xyz.pedestalsimulator.producer.TelemetryProducer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TelemetrySimulator {

    private final TelemetryProducer producer;

    public TelemetrySimulator(TelemetryProducer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRate = 5000)
    public void generateTelemetry() {

        double electricity = ThreadLocalRandom.current()
                .nextDouble(1, 50);

        double water = ThreadLocalRandom.current()
                .nextDouble(1, 20);

        TelemetryEvent event = new TelemetryEvent(
                UUID.randomUUID().toString(),
                "PED-001",
                Instant.now(),
                Math.round(electricity * 100.0) / 100.0,
                Math.round(water * 100.0) / 100.0,
                "ACTIVE"
        );

        producer.send(event);

        System.out.println("Mesure envoyée : " + event);
    }
}
