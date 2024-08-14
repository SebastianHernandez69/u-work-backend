package com.example.proyecto_analisis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Genero;
import com.example.proyecto_analisis.services.impl.GeneroServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class GeneroController {
    @Autowired
    private GeneroServiceImpl generoImpl;

    @GetMapping("/gen/mostrar")
    public List<Genero> mostrarGeneros() {
        return (List<Genero>) generoImpl.mostrarGeneros();
    }
    
}
