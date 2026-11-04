package tn.iset.smartmarina.port_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.iset.smartmarina.port_service.dto.PortRequest;
import tn.iset.smartmarina.port_service.dto.PortResponse;
import tn.iset.smartmarina.port_service.entity.Port;
import tn.iset.smartmarina.port_service.repository.PortRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PortService {

    private final PortRepository repo;

    @Transactional(readOnly = true)
    public List<PortResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PortResponse findById(Long id) {
        return toResponse(get(id));
    }

    public PortResponse create(PortRequest r) {
        Port p = new Port(null, r.name(), r.city(), r.country(), r.capacity());
        return toResponse(repo.save(p));
    }

    public PortResponse update(Long id, PortRequest r) {
        get(id);
        Port p = new Port(id, r.name(), r.city(), r.country(), r.capacity());
        return toResponse(repo.save(p));
    }

    public void delete(Long id) {
        get(id);
        repo.deleteById(id);
    }

    private Port get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Port introuvable : " + id));
    }

    private PortResponse toResponse(Port p) {
        return new PortResponse(p.getId(), p.getName(), p.getCity(), p.getCountry(), p.getCapacity());
    }
}