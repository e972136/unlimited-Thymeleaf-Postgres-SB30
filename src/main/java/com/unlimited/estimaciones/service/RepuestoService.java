package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;

import java.util.List;

public interface RepuestoService {
    RepuestoRequest saveRepuesto(RepuestoRequest repuestoRequest);

    int eliminarRepuesto(int idRepuesto);

    void saveRepuestos(List<Repuesto> repuestos);
}
