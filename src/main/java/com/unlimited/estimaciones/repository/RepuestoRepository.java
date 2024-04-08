package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepuestoRepository extends JpaRepository<Repuesto,Integer> {
    @Query(value = "SELECT SUM(precio) FROM repuesto where estimacion_id=:estimacion_id", nativeQuery = true)
    String selectTotals(Integer estimacion_id);

    List<Repuesto> findAllByEstimacionId(int id);
}
