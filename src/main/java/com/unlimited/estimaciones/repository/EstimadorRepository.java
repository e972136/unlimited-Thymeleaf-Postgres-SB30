package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.Estimador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstimadorRepository extends JpaRepository<Estimador, Integer> {
}
