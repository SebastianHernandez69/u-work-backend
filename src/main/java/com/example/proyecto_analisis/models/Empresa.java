package com.example.proyecto_analisis.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMPRESA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @Column(name = "ID_EMPRESA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpresa;

    @Column(name = "NOMBRE_EMPRESA")
    private String nombreEmpresa;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "CONTRASENA")
    private String contrasena;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "SITIO_WEB")
    private String sitioWeb;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ID_DIRECTOR", nullable = false)
    private int idDirector;

    @Column(name = "ID_INDUSTRIA", nullable = false)
    private int idIndustria;

    @Column(name = "ID_DIRECCION", nullable = false)
    private int idDireccion;

    @Column(name = "URL_LOGO")
    private String url_logo;
}
