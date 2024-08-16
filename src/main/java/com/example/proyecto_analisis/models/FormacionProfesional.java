package com.example.proyecto_analisis.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FORMACION_PROFESIONAL")
@Getter
@Setter
@NoArgsConstructor
public class FormacionProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FORMACION_PROFESIONAL")
    private Integer idFormacionProfesional;

    @Column(name = "FORMACION_PROFESIONAL", length = 100)
    private String formacionProfesional;

}

