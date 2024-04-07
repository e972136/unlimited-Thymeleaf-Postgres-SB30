package com.unlimited.estimaciones.controller;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.dto.EstimacionListado;
import com.unlimited.estimaciones.entity.dto.EstimacionResponse;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endpoint")
public class EstimacionController {
    private final EstimacionService estimacionService;

    public EstimacionController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }

    @GetMapping("/{idEstimacion}")
    EstimacionResponse obtenerEstimacion(
        @PathVariable Integer idEstimacion
    ){
        return estimacionService.findById(idEstimacion);
    }

    @GetMapping("/lista/{idEstimacion}")
    EstimacionListado obtenerEstimacionListado(
            @PathVariable Integer idEstimacion
    ){
        return estimacionService.findByIdListado(idEstimacion);
    }
}
