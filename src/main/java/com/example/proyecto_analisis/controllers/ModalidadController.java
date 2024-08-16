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

import com.example.proyecto_analisis.models.Modalidad;
import com.example.proyecto_analisis.services.ModalidadService;

@RestController
@RequestMapping("/api/modalidad")
public class ModalidadController {
    @Autowired
    private ModalidadService modalidadImpl;

    @GetMapping("/mostrar")
    public List<Modalidad> mostrarModalidades() {
        return (List<Modalidad>) modalidadImpl.mostrarModalidades();
    }

    @PostMapping("/ingresar/{modalidadP}")
    public ResponseEntity<String> ingresarModalidad(@PathVariable String modalidadP){
        try {
            modalidadImpl.ingresarModalidad(modalidadP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/eliminar/{idModalidadP}")
    public ResponseEntity<String> eliminarModalidad(@PathVariable int idModalidadP){
        try {
            modalidadImpl.eliminarModalidadPorId(idModalidadP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
