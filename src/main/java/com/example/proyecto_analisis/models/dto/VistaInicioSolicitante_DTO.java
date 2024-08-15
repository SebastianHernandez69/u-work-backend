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
public class VistaInicioSolicitante_DTO {

    private int idSolicitante;

    private List<Map<String,Object>> notificaciones;

    private List<Map<String,Object>> ofertas;

    private List<Map<String,Object>> categorias;

}
