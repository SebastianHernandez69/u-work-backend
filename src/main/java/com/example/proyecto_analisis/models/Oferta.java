package com.example.proyecto_analisis.models;

import java.util.Date;

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
@Table(name = "OFERTAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OFERTA")
    private Integer idOferta;

    @Column(name = "ID_EMPRESA", nullable = false)
    private Integer idEmpresa;

    @Column(name = "ID_TIPO_EMPLEO", nullable = false)
    private Integer idTipoEmpleo;

    @Column(name = "ID_NIVEL_ACADEMICO", nullable = false)
    private Integer idNivelAcademico;

    @Column(name = "ID_LUGAR", nullable = false)
    private Integer idLugar;

    @Column(name = "ID_MODALIDAD", nullable = false)
    private Integer idModalidad;

    @Column(name = "ID_CONTRATO", nullable = false)
    private Integer idContrato;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRIPCION", length = 5000)
    private String descripcion;

    @Column(name = "FECHA_PUBLICACION")
    private Date fechaPublicacion;

    @Column(name = "PLAZAS_DISPONIBLES")
    private Integer plazasDisponibles;

    @Column(name = "ESTADO_OFERTA")
    private Boolean estadoOferta;

    @Column(name = "FECHA_EXPIRACION")
    private Date fechaExpiracion;
}
