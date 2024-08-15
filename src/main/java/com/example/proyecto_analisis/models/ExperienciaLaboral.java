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
@Table(name = "experiencia_laboral")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaLaboral {
    @Id
    @Column(name = "idEXPERIENCIA_LABORAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExperienciaLaboral;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "ID_PUESTO")
    private Integer idPuesto;

    @Column(name = "EMPRESA", length = 45)
    private String empresa;

    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;

    @Column(name = "FECHA_FIN")
    private Date fechaFin;

    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;
}
