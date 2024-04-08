package com.unlimited.estimaciones.entity.dto;

import java.math.BigDecimal;

public record RepuestoRequest(Integer idEstimacion, Integer idRepuesto, String descripcion, BigDecimal precio) {
}
