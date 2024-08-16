package com.example.proyecto_analisis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.dto.OfertaEmpresaHomeDTO;
import com.example.proyecto_analisis.services.OfertaService;

@RestController
@RequestMapping("/api")
public class EmpresaController {
    
    @Autowired
    private OfertaService ofertaServiceImpl;

    @GetMapping("/empresa/home/{idEmpresaP}")
    public ResponseEntity<Object> obtenerHomeEmpresa(@PathVariable int idEmpresaP){

        try {
            OfertaEmpresaHomeDTO homeEmpresa = ofertaServiceImpl.obtenerHomeEmpresa(idEmpresaP);
            return ResponseEntity.ok(homeEmpresa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener informacion: "+e.getMessage());
        }

    }

}
