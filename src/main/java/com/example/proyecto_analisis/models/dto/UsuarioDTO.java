package com.example.proyecto_analisis.models.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String identificacion;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String correo;

    private String contrasena;

    private String telefono;

    private Date fechaNacimiento;

    private String titular;
    
    private String idPaisResidencia;

    private String idDepartamentoResidencia;
    
    private String idMunicipioResidencia;

    private String idLugarNacimiento;

    private String idEstadoCivil;

    private String idGenero;

    private String descripcion;
}
