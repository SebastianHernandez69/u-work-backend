package com.example.proyecto_analisis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.dto.OfertaDTO;
import com.example.proyecto_analisis.services.OfertaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasController {
    
    @Autowired
    private OfertaService ofertaService;

    @GetMapping("/detalle/{idOferta}")
    public OfertaDTO obtenerDetalleOferta(@PathVariable int idOferta) {
        return ofertaService.obtenerDetalleOferta(idOferta);
    }

}
