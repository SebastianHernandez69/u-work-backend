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
@Table(name = "INDUSTRIAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Industria {

    @Id
    @Column(name = "ID_INDUSTRIA")
    private Integer idIndustria;

    @Column(name = "INDUSTRIA")
    private String industria;
}
