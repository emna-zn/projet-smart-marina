package tn.iset.smartmarina.consumption_service.enums;

/** Ressource fournie par une borne, avec son unite de mesure. */
public enum ResourceType {
    ELECTRICITY("kWh"),
    WATER("m3"),
    GAS("m3");

    private final String unit;

    ResourceType(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}