package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.dto.EstimacionListado;
import com.unlimited.estimaciones.entity.dto.EstimacionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstimacionService {

    EstimacionResponse findById(int id);



    EstimacionResponse saveEstimacion(EstimacionResponse estimacion);


    EstimacionListado findByIdListado(Integer idEstimacion);

    Page<EstimacionListado> traerListado(Pageable page);

    Page<EstimacionListado> traerListadoFiltrado(String busqueda, Pageable page);
}
