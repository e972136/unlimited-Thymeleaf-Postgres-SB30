package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.Estimacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimacionRepository extends JpaRepository<Estimacion,Integer> {
    Page<Estimacion> findAllByPlacaContainingIgnoreCaseOrAseguradoContainingIgnoreCaseOrId(String busqueda, String busqueda1, Integer s, Pageable page);
}
