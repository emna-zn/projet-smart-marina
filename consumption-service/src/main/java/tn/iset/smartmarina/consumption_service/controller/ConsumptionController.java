package tn.iset.smartmarina.consumption_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.iset.smartmarina.consumption_service.dto.ConsumptionRequest;
import tn.iset.smartmarina.consumption_service.dto.ConsumptionResponse;
import tn.iset.smartmarina.consumption_service.service.ConsumptionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumptions")
public class ConsumptionController {

    private final ConsumptionService service;

    public ConsumptionController(ConsumptionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumptionResponse create(@Valid @RequestBody ConsumptionRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ConsumptionResponse> findAll(@RequestParam(required = false) Long pedestalId) {
        return service.findAll(pedestalId);
    }

    @GetMapping("/{id}")
    public ConsumptionResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ConsumptionResponse update(@PathVariable Long id,
                                      @Valid @RequestBody ConsumptionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}