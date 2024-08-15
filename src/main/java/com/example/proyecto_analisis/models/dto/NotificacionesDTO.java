package com.example.proyecto_analisis.models.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionesDTO {
    
    private String idNotificacion;

    private String titulo;

    private Date fecha;

    private boolean estado;

    private String urlLogo;

}
