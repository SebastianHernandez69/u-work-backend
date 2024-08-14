package com.example.proyecto_analisis.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDirectorDTO {

    private String nombreEmpresa;

    private String correoEmpresa;

    private String contrasena;

    private String telefonoEmpresa;

    private String idDireccionPais;

    private String idIndustria;

    private String sitioWeb;

    //Datos director
    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String telefonoPersona;

    private String identificacion;

    private String idGenero;
}
