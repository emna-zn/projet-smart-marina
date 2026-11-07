package tn.iset.smartmarina.consumption_service.entity;

import jakarta.persistence.*;
import tn.iset.smartmarina.consumption_service.enums.ResourceType;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "consumptions")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identifiant de la borne (pedestal-service). Simple nombre : PAS de
    // relation JPA entre microservices, chaque service a sa propre base.
    @Column(name = "pedestal_id", nullable = false)
    private Long pedestalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", nullable = false, length = 20)
    private ResourceType resourceType;

    @Column(name = "measured_value", nullable = false, precision = 14, scale = 3)
    private BigDecimal value;

    @Column(nullable = false, length = 10)
    private String unit;

    @Column(name = "measured_at", nullable = false)
    private Instant measuredAt;

    public Consumption() {
    }

    public Long getId() { return id; }

    public Long getPedestalId() { return pedestalId; }
    public void setPedestalId(Long pedestalId) { this.pedestalId = pedestalId; }

    public ResourceType getResourceType() { return resourceType; }
    public void setResourceType(ResourceType resourceType) { this.resourceType = resourceType; }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Instant getMeasuredAt() { return measuredAt; }
    public void setMeasuredAt(Instant measuredAt) { this.measuredAt = measuredAt; }
}