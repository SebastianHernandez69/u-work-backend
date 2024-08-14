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
@Table(name = "parentescos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parentesco {
    
    @Id
    @Column(name = "id_parentescos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_parentescos;

    private String parentesco;
}
