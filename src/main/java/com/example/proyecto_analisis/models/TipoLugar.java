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
@Table(name = "TIPO_LUGAR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoLugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_LUGAR")
    private int idTipoLugar;

    @Column(name = "TIPO_LUGAR", length = 100)
    private String tipoLugar;
}

