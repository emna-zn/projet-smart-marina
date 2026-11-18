package com.xyz.monitoringservice.consumer;

import com.xyz.monitoringservice.dto.TelemetryEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TelemetryConsumer {

    private final Map<String, TelemetryEvent> latestTelemetry =
            new ConcurrentHashMap<>();

    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(TelemetryEvent event) {

        latestTelemetry.put(event.pedestalId(), event);

        System.out.println("Nouvelle mesure reçue : " + event);
    }

    public TelemetryEvent getLatestTelemetry(String pedestalId) {
        return latestTelemetry.get(pedestalId);
    }
}