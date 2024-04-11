package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReparacionRepository extends JpaRepository<Reparacion,Integer> {
    @Query(value = "SELECT SUM(precio) FROM reparacion where estimacion_id=:estimacion_id", nativeQuery = true)
    String selectTotals(Integer estimacion_id);

    List<Reparacion> findAllByEstimacionIdOrderById(int id);
}
