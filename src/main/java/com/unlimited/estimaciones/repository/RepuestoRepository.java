package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepuestoRepository extends JpaRepository<Repuesto,Integer> {
    @Query(value = "SELECT SUM(precio) FROM repuesto where estimacion_id=:estimacion_id", nativeQuery = true)
    String selectTotals(Integer estimacion_id);

    String traerRepuestos = "SELECT DISTINCT descripcion \n" +
            "FROM repuesto \n" +
            "where descripcion not like '%*%'  \n" +
            "and descripcion not like '%(%'  \n" +
            "and descripcion not like '%-%'  \n" +
            "and descripcion not like '%.%'  \n" +
            "and descripcion not like '1%'  \n" +
            "and descripcion not like '2%'  \n" +
            "and descripcion not like '3%'  \n" +
            "and descripcion not like '4%'  \n" +
            "and descripcion not like '5%'  \n" +
            "and descripcion not like '6%'  \n" +
            "and descripcion not like '7%'  \n" +
            "and descripcion not like '8%'  \n" +
            "and descripcion not like '9%'  \n" +
            "order by descripcion";

    @Query(value =traerRepuestos, nativeQuery = true)
    List<String> selectDescripciones();

    List<Repuesto> findAllByEstimacionIdOrderByIdAsc(int id);
}
