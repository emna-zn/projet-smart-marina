
package com.xyz.pedestalsimulator.producer;

import com.xyz.pedestalsimulator.dto.TelemetryEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryProducer {

    private final KafkaTemplate<String, TelemetryEvent> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public TelemetryProducer(
            KafkaTemplate<String, TelemetryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(TelemetryEvent event) {
        kafkaTemplate.send(topic, event.pedestalId(), event);
    }
}
