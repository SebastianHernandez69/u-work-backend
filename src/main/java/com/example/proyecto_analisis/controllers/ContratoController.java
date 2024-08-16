package com.example.proyecto_analisis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Contrato;
import com.example.proyecto_analisis.services.ContratoService;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {
    
    @Autowired
    private ContratoService contratoimpl;

    @GetMapping("/mostrar")
    public List<Contrato> mostrarContratos() {
        return (List<Contrato>) contratoimpl.mostrarContratos();
    }

    @PostMapping("ingresar/{contratoP}")
    public ResponseEntity<String> ingresarContrato(@PathVariable String contratoP){
        try {
            contratoimpl.ingresarContrato(contratoP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/eliminar/{idContratoP}")
    public ResponseEntity<String> eliminarContrato(@PathVariable int idContratoP){
        try {
            contratoimpl.eliminarContratoPorID(idContratoP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
