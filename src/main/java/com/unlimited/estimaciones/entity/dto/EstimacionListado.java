package com.unlimited.estimaciones.entity.dto;

public record EstimacionListado(
        int id,
        String placa,
        String asegurado,
        String aseguradora,
        String estimadoPor,
        String fechaEvaluacion
) {

}
