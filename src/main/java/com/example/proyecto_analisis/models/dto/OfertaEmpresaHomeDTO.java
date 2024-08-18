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
public class OfertaEmpresaHomeDTO {
    
    private String nombreEmpresa;

    private String logoEmpresa;

    private String cantOfertasAct;

    private String promSolicitanteOfer;

    private String porcentajeHombresAplicante;
    
    private String porcentajeMujeresAplicante;

    private List<Object[]> ofertasActivas;

    private List<Object[]> notificaciones;
}
