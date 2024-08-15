package com.example.proyecto_analisis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamiliarSolicitanteDTO {

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String telefono; //int

    private String identificacion;

    private Integer idGenero;

    private Integer idParentesco;

}
