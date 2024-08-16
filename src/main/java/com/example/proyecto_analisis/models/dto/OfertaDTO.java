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
public class OfertaDTO {
    
    private String nombreOferta;
    
    private String urlEmpresa;

    private String nombreEmpresa;

    private String fechaPublicacion;

    private String fechaExpiracion;

    private String lugar;

    private String tipoEmpleo;

    private List<String> cargos;

    private int vacantes;

    private int cantidadAplicantes;

    private int aplicando;

    private String tipoContratacion;

    private String modalidad;

    private String nivelAcademico;

    private String descripcion;

    private List<String> requisitosAcademicos;

    private List<String> experienciaRequerida;

    private List<Map<String, Object>> idiomas;

    private List<String> aplicantesImg;
}
