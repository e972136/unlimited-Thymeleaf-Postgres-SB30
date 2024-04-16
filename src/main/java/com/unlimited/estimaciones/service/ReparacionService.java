package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.entity.Reparacion;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;

import java.util.List;

public interface ReparacionService {
    List<Reparacion> findAllByEstimacionIdOrderById(int id);

    RepuestoRequest saveReparacion(RepuestoRequest reparacionRequest);

    void saveReparaciones(List<Reparacion> reparaciones);
}
