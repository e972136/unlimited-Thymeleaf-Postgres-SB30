package com.unlimited.estimaciones.service.impl;

import com.unlimited.estimaciones.entity.ReparacionAdicional;
import com.unlimited.estimaciones.repository.ReparacionAdicionalRepository;
import com.unlimited.estimaciones.service.ReparacionesAdicionalesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReparacionesAdicionalesServiceImpl implements ReparacionesAdicionalesService {

    private final ReparacionAdicionalRepository reparacionAdicionalRepository;

    public ReparacionesAdicionalesServiceImpl(ReparacionAdicionalRepository reparacionAdicionalRepository) {
        this.reparacionAdicionalRepository = reparacionAdicionalRepository;
    }

    @Override
    public List<ReparacionAdicional> findAllByEstimacionIdOrderById(int id) {
        return reparacionAdicionalRepository.findAllByEstimacionIdOrderById(id);
    }
}
