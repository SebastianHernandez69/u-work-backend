package com.example.proyecto_analisis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Genero;
import com.example.proyecto_analisis.repository.GeneroRepository;
import com.example.proyecto_analisis.services.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepositorio;

    @Override
    public List<Genero> mostrarGeneros() {
        return (List<Genero>) generoRepositorio.findAll();
    }

    @Override
    public Genero obtenerGeneroPorId(int idGenero) {
        return generoRepositorio.findById(idGenero)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado"));
    }

    @Override
    public void eliminarGeneroPorId(int idGenero) {
        generoRepositorio.deleteById(idGenero);
    }

    @Override
    public void ingresarGenero(String genero) {
        generoRepositorio.ingresarGenero(genero);
    }
    
}
