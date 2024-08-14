package com.example.proyecto_analisis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaPA_DTO {
    private String puesto;

    private String empresa;

    private String fechaInicio;

    private String fechaFinal;
}
