package com.example.proyecto_analisis.models.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormacionPA_DTO {
    
    private String titulo;

    private String empresa;

    private String fechaExpedicion;

    private String nivelAcademico;
}

