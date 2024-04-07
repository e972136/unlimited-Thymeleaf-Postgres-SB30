package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.ReparacionAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReparacionAdicionalRepository extends JpaRepository<ReparacionAdicional,Integer> {
    @Query(value = "SELECT SUM(valor_reparacion) FROM reparacion_adicional where estimacion_id=:estimacion_id", nativeQuery = true)
    String selectTotals(Integer estimacion_id);

}
