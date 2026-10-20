package tn.iset.smartmarina.port_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.iset.smartmarina.port_service.dto.PortRequest;
import tn.iset.smartmarina.port_service.dto.PortResponse;
import tn.iset.smartmarina.port_service.entity.Port;
import tn.iset.smartmarina.port_service.repository.PortRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortService {

    private final PortRepository repo;

    public List<PortResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public PortResponse findById(Long id) {
        return toResponse(get(id));
    }

    public PortResponse create(PortRequest r) {
        Port p = new Port(null, r.name(), r.city(), r.country(), r.capacity());
        return toResponse(repo.save(p));
    }

    public PortResponse update(Long id, PortRequest r) {
        get(id); // erreur 404 si le port n'existe pas
        Port p = new Port(id, r.name(), r.city(), r.country(), r.capacity());
        return toResponse(repo.save(p));
    }

    public void delete(Long id) {
        get(id); // erreur 404 si le port n'existe pas
        repo.deleteById(id);
    }

    // Cherche un port par id, sinon erreur 404
    private Port get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Port introuvable"));
    }

    // Transforme un Port en PortResponse
    private PortResponse toResponse(Port p) {
        return new PortResponse(p.getId(), p.getName(), p.getCity(), p.getCountry(), p.getCapacity());
    }
}