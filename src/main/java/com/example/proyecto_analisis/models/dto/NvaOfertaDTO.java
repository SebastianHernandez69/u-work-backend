package com.example.proyecto_analisis.models.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NvaOfertaDTO {
     
    private String titulo;
    
    private int plazasDisponibles;
    
    private Date fechaExpiracion;
    
    private String descripcion;
    
    private int tipoEmpleo;
    
    private int tipoContrato;
    
    private int nivelAcademico;
    
    private int modalidad;
    
    private List<Integer> puestos;
    
    private int lugar;
    
    private List<Map<String,Integer>> idiomas;
}
