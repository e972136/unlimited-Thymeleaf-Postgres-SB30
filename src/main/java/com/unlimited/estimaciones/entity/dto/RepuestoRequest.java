package com.unlimited.estimaciones.entity.dto;

import java.math.BigDecimal;

public record RepuestoRequest(Integer idEstimacion, Integer idRepuesto, String descripcion,Integer cantidad,  BigDecimal precio, String tipo) {
}
