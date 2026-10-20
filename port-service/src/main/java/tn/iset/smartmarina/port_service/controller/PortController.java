package tn.iset.smartmarina.port_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.iset.smartmarina.port_service.dto.PortRequest;
import tn.iset.smartmarina.port_service.dto.PortResponse;
import tn.iset.smartmarina.port_service.service.PortService;

import java.util.List;

@RestController
@RequestMapping("/api/ports")
@RequiredArgsConstructor
public class PortController {

    private final PortService service;

    @GetMapping
    public List<PortResponse> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PortResponse one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortResponse create(@Valid @RequestBody PortRequest r) {
        return service.create(r);
    }

    @PutMapping("/{id}")
    public PortResponse update(@PathVariable Long id, @Valid @RequestBody PortRequest r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}