package tn.iset.smartmarina.consumption_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.iset.smartmarina.consumption_service.dto.ConsumptionRequest;
import tn.iset.smartmarina.consumption_service.dto.ConsumptionResponse;
import tn.iset.smartmarina.consumption_service.entity.Consumption;
import tn.iset.smartmarina.consumption_service.exception.ConsumptionNotFoundException;
import tn.iset.smartmarina.consumption_service.repository.ConsumptionRepository;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ConsumptionService {

    private final ConsumptionRepository repository;

    public ConsumptionService(ConsumptionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ConsumptionResponse> findAll(Long pedestalId) {
        List<Consumption> list = (pedestalId == null)
                ? repository.findAll()
                : repository.findByPedestalIdOrderByMeasuredAtDesc(pedestalId);
        return list.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ConsumptionResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public ConsumptionResponse create(ConsumptionRequest request) {
        Consumption c = new Consumption();
        apply(request, c);
        return toResponse(repository.save(c));
    }

    public ConsumptionResponse update(Long id, ConsumptionRequest request) {
        Consumption c = getOrThrow(id);
        apply(request, c);
        return toResponse(repository.save(c));
    }

    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    private Consumption getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new ConsumptionNotFoundException(id));
    }

    private void apply(ConsumptionRequest request, Consumption c) {
        c.setPedestalId(request.pedestalId());
        c.setResourceType(request.resourceType());
        c.setValue(request.value());
        // L'unite est deduite de la ressource : le client ne peut pas se tromper.
        c.setUnit(request.resourceType().getUnit());
        c.setMeasuredAt(request.measuredAt() != null ? request.measuredAt() : Instant.now());
    }

    private ConsumptionResponse toResponse(Consumption c) {
        return new ConsumptionResponse(
                c.getId(), c.getPedestalId(), c.getResourceType(),
                c.getValue(), c.getUnit(), c.getMeasuredAt());
    }
}