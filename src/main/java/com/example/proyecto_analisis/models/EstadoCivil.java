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
@Table(name = "estado_civil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCivil {
    
    @Id
    @Column(name = "id_estado_civil")
    private int id_estado_civil;

    @Column(name = "estado_civil")
    private String estado_civil;

}
