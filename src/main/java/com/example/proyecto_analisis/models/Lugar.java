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
@Table(name = "lugares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lugar {
    
    @Id
    @Column(name = "id_lugar")
    private int id_lugar;

    @Column(name = "nombre_lugar")
    private String nombre_lugar;

    @Column(name = "id_tipo_lugar")
    private int id_tipo_lugar;

    @Column(name = "id_lugar_padre")
    private Integer id_lugar_padre;

}
