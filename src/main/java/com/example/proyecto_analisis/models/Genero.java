package com.example.proyecto_analisis.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GENEROS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Genero {
    
    @Id
    @Column(name = "id_genero")
    private int id_genero;

    @Column(name = "genero")
    private String genero;

}
