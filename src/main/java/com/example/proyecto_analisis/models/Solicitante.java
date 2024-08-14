package com.example.proyecto_analisis.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "solicitantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitante {

    @Id
    @Column(name = "ID_PERSONA")
    private Integer idPersona;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "CONTRASENA")
    private String contrasena;

    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;

    @Column(name = "TITULAR",nullable = true)
    private String titular;

    @Column(name = "DESCRIPCION",nullable = true)
    private String descripcion;

    @Column(name = "id_estado_civil")
    private int estadoCivil;

    @Column(name = "id_lugar_nacimiento")
    private int lugarNacimiento;

    @Column(name = "id_lugar_residencia")
    private int lugarResidencia;
}