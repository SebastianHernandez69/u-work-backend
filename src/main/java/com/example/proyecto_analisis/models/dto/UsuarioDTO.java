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

    private String Titular;
    
    private String idLugarResidencia;

    private String idLugarNacimiento;

    private String idEstadoCivil;

    private String idGenero;

    private String descripcion;
}
