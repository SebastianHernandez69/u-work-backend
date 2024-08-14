package com.example.proyecto_analisis.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidacionPersona {
    
    private Boolean validacion;

    private String nombreCompleto;

    private List<String> familiares;
}
