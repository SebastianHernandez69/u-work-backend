package com.example.proyecto_analisis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SegurosPA_DTO {
    
    private String titulo;

    private String fechaAfiliacion;

    private String fechaExpiracion;

    private String numeroAfiliacion;
}
