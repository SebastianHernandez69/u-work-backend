package com.example.proyecto_analisis.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilAplicanteDTO {
    
    private DatosPA_DTO datosPersonales;

    private List<FormacionPA_DTO> formacion;

    private List<HistorialMedicoPA_DTO> historialMedico;

    private List<SegurosPA_DTO> seguros;

    private List<IdiomasPA_DTO> idiomas;

    private List<ExperienciaPA_DTO> experiencia;

    private List<FamiliaresPA_DTO> familiares;
}
