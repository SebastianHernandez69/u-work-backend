package com.example.proyecto_analisis.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActPreferenciasDTO {
    
    private List<Integer> puestos;

    private List<Integer> modalidaes;

    private List<Integer> contratos;
}
