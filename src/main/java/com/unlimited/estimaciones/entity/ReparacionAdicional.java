package com.unlimited.estimaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReparacionAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer estimacionId;

    @Column(name = "reparacionAdicional")
    String reparacionAdicionalDetalle;

    BigDecimal valorReparacion;
    String tipo;
}
