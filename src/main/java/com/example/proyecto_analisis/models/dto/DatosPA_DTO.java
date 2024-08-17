package com.example.proyecto_analisis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DatosPA_DTO {
    
    private String nombre;

    private String titular;

    private String lugarResidencia;

    private String telefono;

    private String correo;

    private String fechaNacimiento;

    private String descripcion;
}
