package com.example.proyecto_analisis.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NIVEL_ACADEMICO")
@Getter
@Setter
@NoArgsConstructor
public class NivelAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NIVEL_ACADEMICO")
    private Integer idNivelAcademico;

    @Column(name = "NIVEL_ACADEMICO", length = 45)
    private String nivelAcademico;
}
