package com.example.proyecto_analisis.models.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreferenciasUsuarioDTO {
    
    private List<Map<String, Object>> puestos;

    private List<Map<String, Object>> modalidades;

    private List<Map<String, Object>> contratos;

}
