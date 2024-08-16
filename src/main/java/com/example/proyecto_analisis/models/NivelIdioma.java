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
@Table(name = "NIVEL_IDIOMA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NivelIdioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NIVEL_IDIOMA")
    private int idNivelIdioma;

    @Column(name = "NIVEL_IDIOMA", length = 45)
    private String nivelIdioma;
}

