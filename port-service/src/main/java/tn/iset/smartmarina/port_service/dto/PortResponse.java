package tn.iset.smartmarina.port_service.dto;

public record PortResponse(Long id, String name, String city, String country, int capacity) {}