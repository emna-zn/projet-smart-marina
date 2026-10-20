package tn.iset.smartmarina.port_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PortRequest(
        @NotBlank String name,
        @NotBlank String city,
        @NotBlank String country,
        @Min(1) int capacity) {}