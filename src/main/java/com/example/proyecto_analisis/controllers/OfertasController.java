package com.example.proyecto_analisis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.dto.OfertaDTO;
import com.example.proyecto_analisis.services.OfertaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/ofertas")
public class OfertasController {
    
    @Autowired
    private OfertaService ofertaService;

    @GetMapping("/detalle")
    public ResponseEntity<OfertaDTO> obtenerDetalleOferta(@RequestParam("idOferta") int idOferta, @RequestParam("idSolicitante") int idSolicitante) {
        try {
            return ResponseEntity.ok(ofertaService.obtenerDetalleOferta(idOferta, idSolicitante));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/detalleEmpresa/{idOferta}")
    public ResponseEntity<OfertaDTO> obtenerDetalleOfertaEmpresa(@PathVariable int idOferta) {
        try {
            return ResponseEntity.ok(ofertaService.obtenerDetalleOfertaEmpresa(idOferta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    

}
