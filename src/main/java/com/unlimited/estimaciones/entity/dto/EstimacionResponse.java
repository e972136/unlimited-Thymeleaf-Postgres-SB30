package com.unlimited.estimaciones.entity.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public record EstimacionResponse(
        int id,
        String asegurado,
        String estimadoPor,
        String fechaEvaluacion,
        String aseguradora,
        String placa,
        String marca,
        String modelo,
        String kilometraje,
        String colorVehiculo,
        String anioVehiculo,
        String vinOSerie,
        BigDecimal totalCostosManoObra,
        BigDecimal totalCostosAdicionalesMateriales,
        BigDecimal costoManoDeObraGerente,
        BigDecimal costoMaterialesGerente,
        String obs,
        String implementadoPor,
        String nFechaIngreso,
        String nEstadoVehiculo,
        String nFechaEntrega,
        String nHoraEntrega,
        String nPintor,
        String nAjustadorAseguradora,
        BigDecimal nCostoMaterialGastado,
        BigDecimal totalRepuestos,
        BigDecimal totalReparaciones,
        BigDecimal totalReparacionesAdicionales,

        BigDecimal granTotal
) {
    public BigDecimal granTotal(){
        double _totalRepuestos = isNull(totalRepuestos)?0.0:totalRepuestos.doubleValue();
        double _totalCostosManoObra = isNull(totalCostosManoObra)?0.0:totalCostosManoObra.doubleValue();
        return new BigDecimal(_totalRepuestos+_totalCostosManoObra);
    }
}
