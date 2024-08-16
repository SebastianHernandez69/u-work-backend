package com.example.proyecto_analisis.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TIPO_EMPLEO")
@Getter
@Setter
@NoArgsConstructor
public class TipoEmpleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_EMPLEO")
    private Integer idTipoEmpleo;

    @Column(name = "TIPO_EMPLEO", length = 45)
    private String tipoEmpleo;

}

