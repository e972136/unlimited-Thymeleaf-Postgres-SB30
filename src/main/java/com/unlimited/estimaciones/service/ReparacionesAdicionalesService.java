package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.entity.ReparacionAdicional;

import java.util.List;

public interface ReparacionesAdicionalesService {
    List<ReparacionAdicional> findAllByEstimacionIdOrderById(int id);
}
