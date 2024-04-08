package com.unlimited.estimaciones.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estimador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nombreEstimador;

    boolean activo;
}
