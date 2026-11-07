package tn.iset.smartmarina.consumption_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iset.smartmarina.consumption_service.entity.Consumption;

import java.util.List;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    List<Consumption> findByPedestalIdOrderByMeasuredAtDesc(Long pedestalId);
}