package com.example.proyecto_analisis.models.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VistaPerfilEmpresa_DTO {
    
    private int idEmpresa;

    private String urlFotoEmpresa;

    private String nombreEmpresa;

    private String nombreIndustria;

    private Long ofertasPublicadas;
    
    private String pais;

    private String descripcion;

    private String telefono;

    private String correo;

    private String sitioWeb;

    private List<Map<String,Object>> director;

    private List<Map<String,Object>> ofertas;


}
