package com.example.proyecto_analisis.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CONDICIONES_MEDICAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondicionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONDICIONE_MEDICA")
    private Integer idCondicioneMedica;

    @Column(name = "CONDICION_MEDICAS", length = 45)
    private String condicionMedicas;

}