package tn.iset.smartmarina.consumption_service.dto;

import tn.iset.smartmarina.consumption_service.enums.ResourceType;

import java.math.BigDecimal;
import java.time.Instant;

public record ConsumptionResponse(
        Long id,
        Long pedestalId,
        ResourceType resourceType,
        BigDecimal value,
        String unit,
        Instant measuredAt) {
}