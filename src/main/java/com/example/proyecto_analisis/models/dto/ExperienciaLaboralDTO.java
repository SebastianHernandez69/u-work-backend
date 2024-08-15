package com.example.proyecto_analisis.models.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaLaboralDTO {

    private String empresa;

    private Date fechaInicio;

    private Date fechaFin;

    private Integer puestoOcupado;

    private String descripcion;
}
