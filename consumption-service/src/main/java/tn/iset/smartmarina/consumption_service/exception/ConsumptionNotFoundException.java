package tn.iset.smartmarina.consumption_service.exception;

public class ConsumptionNotFoundException extends RuntimeException {

    public ConsumptionNotFoundException(Long id) {
        super("Consommation introuvable : " + id);
    }
}