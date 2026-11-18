
package com.xyz.monitoringservice.dto;

import java.time.Instant;

public record TelemetryEvent(
        String eventId,
        String pedestalId,
        Instant measuredAt,
        double electricityKwh,
        double waterM3,
        String status
) {
}
