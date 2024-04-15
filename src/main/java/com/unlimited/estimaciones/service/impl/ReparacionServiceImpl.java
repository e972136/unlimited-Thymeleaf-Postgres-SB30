package com.unlimited.estimaciones.service.impl;

import com.unlimited.estimaciones.entity.Reparacion;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.repository.ReparacionRepository;
import com.unlimited.estimaciones.service.ReparacionService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReparacionServiceImpl implements ReparacionService {

    private final ReparacionRepository reparacionRepository;

    public ReparacionServiceImpl(ReparacionRepository reparacionRepository) {
        this.reparacionRepository = reparacionRepository;
    }

    @Override
    public List<Reparacion> findAllByEstimacionIdOrderById(int id) {
        return reparacionRepository.findAllByEstimacionIdOrderById(id);
    }

    @Override
    public RepuestoRequest saveReparacion(RepuestoRequest reparacionRequest) {
        Reparacion reparacion = new Reparacion();
        reparacion.setDetalleReparacion(reparacionRequest.descripcion());
        reparacion.setEstimacionId(reparacionRequest.idEstimacion());
        reparacion.setPrecio(reparacionRequest.precio());
        Reparacion save = reparacionRepository.save(reparacion);
        return new RepuestoRequest(save.getEstimacionId(),save.getId(),save.getDetalleReparacion(), 1,save.getPrecio());
    }


    /*
        @Override
    public RepuestoRequest saveRepuesto(RepuestoRequest repuestoRequest) {
        Repuesto repuesto= new Repuesto();
        repuesto.setEstimacionId(repuestoRequest.idEstimacion());
        repuesto.setDescripcion(repuestoRequest.descripcion());
        repuesto.setPrecio(repuestoRequest.precio());
        Repuesto save = repuestoRepository.save(repuesto);
        return new RepuestoRequest(save.getEstimacionId(),save.getId(),save.getDescripcion(),save.getPrecio());
    }
     */
}
