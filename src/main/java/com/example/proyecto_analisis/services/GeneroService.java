package com.example.proyecto_analisis.services;

import java.util.List;

import com.example.proyecto_analisis.models.Genero;

public interface GeneroService {
    
    public List<Genero> mostrarGeneros();

    public Genero obtenerGeneroPorId(int idGenero);

    public void eliminarGeneroPorId(int idGenero);

    public void ingresarGenero(String genero);
}
