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

import com.example.proyecto_analisis.models.NivelAcademico;
import com.example.proyecto_analisis.services.NivelAcademicoService;

@RestController
@RequestMapping("/api/nivel-academico")
public class NivelAcademicoController {
    @Autowired
    private NivelAcademicoService nivelAcademicoImpl;

    @GetMapping("/mostrar")
    public List<NivelAcademico> mostrarNivelesAcademicos() {
        return (List<NivelAcademico>) nivelAcademicoImpl.mostrarNivelesAcademicos();
    }

    @PostMapping("/ingresar/{nivelAcademicoP}")
    public ResponseEntity<String> ingresarNivelAcademico(@PathVariable String nivelAcademicoP){
        try {
            nivelAcademicoImpl.ingresarNivelAcademico(nivelAcademicoP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/eliminar/{idNivelAcademico}")
    public ResponseEntity<String> eliminarNivelAcademico(@PathVariable int idNivelAcademico){
        try {
            nivelAcademicoImpl.eliminarNivelAcademicoPorId(idNivelAcademico);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
