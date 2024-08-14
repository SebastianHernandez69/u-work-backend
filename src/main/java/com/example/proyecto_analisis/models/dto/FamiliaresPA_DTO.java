package com.example.proyecto_analisis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamiliaresPA_DTO {
    
    private String nombre;

    private String identificacion;

    private String telefono;

    private String parentesco;
}
