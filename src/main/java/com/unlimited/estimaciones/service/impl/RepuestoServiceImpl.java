package com.unlimited.estimaciones.service.impl;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.repository.RepuestoRepository;
import com.unlimited.estimaciones.service.RepuestoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImpl implements RepuestoService {

    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public RepuestoRequest saveRepuesto(RepuestoRequest repuestoRequest) {
        Repuesto repuesto= new Repuesto();
        repuesto.setEstimacionId(repuestoRequest.idEstimacion());
        repuesto.setDescripcion(repuestoRequest.descripcion());
        repuesto.setPrecio(repuestoRequest.precio());
        Repuesto save = repuestoRepository.save(repuesto);
        return new RepuestoRequest(save.getEstimacionId(),save.getId(),save.getDescripcion(),save.getPrecio());
    }

    @Override
    public int eliminarRepuesto(int idRepuesto) {
        Repuesto repuesto = repuestoRepository.findById(idRepuesto).get();
        repuestoRepository.delete(repuesto);
        return repuesto.getEstimacionId();
    }

    @Override
    public void saveRepuestos(List<Repuesto> repuestos) {
        repuestoRepository.saveAll(repuestos);
    }
/*
*     public Estimacion saveRepuestos(Estimacion estimacion) {
        Estimacion estimacionDB = estimacionRepository.findById(estimacion.getId()).orElse(null);
        List<Repuesto> repuestos = estimacion.getRepuestos();
        estimacionDB.getRepuestos().forEach(r->{
            Repuesto repuesto = repuestos.stream().filter(a -> a.getId() == r.getId()).findAny().get();
            r.setDescripcion(repuesto.getDescripcion());
            r.setPrecio(repuesto.getPrecio());
        });
        return estimacionDB;
    }
*
* */

}
