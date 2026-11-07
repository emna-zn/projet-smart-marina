package tn.iset.smartmarina.consumption_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;
import tn.iset.smartmarina.consumption_service.enums.ResourceType;

import java.math.BigDecimal;
import java.time.Instant;

public record ConsumptionRequest(
        @NotNull @Positive Long pedestalId,
        @NotNull ResourceType resourceType,
        @NotNull @Positive BigDecimal value,
        @PastOrPresent Instant measuredAt) {
}