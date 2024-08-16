package com.example.proyecto_analisis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    
    private int idOferta;

    private int idSolicitante;

    private int idEstadoSolicitud;

    private int emisorSolicitud;

    private String descripcion;

}
