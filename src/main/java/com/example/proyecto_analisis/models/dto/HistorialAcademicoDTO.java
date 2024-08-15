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
public class HistorialAcademicoDTO {
    
    private Integer idNivelAcademico;

    private Integer idFormacionProf;

    private String titulo;

    private Date fechaEgreso;

    private String institucion;
}
