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

import com.example.proyecto_analisis.models.FormacionProfesional;
import com.example.proyecto_analisis.services.FormProfesionalService;

@RestController
@RequestMapping("/api/formacion-prof")
public class FormacionProfController {
    @Autowired
    private FormProfesionalService formProfesionalImpl;

    @GetMapping("/mostrar")
    public List<FormacionProfesional> mostrarFormacionProf() {
        return (List<FormacionProfesional>) formProfesionalImpl.mostrarFormacionProfesionals();
    }

    @PostMapping("/ingresar/{formacionProfP}")
    public ResponseEntity<String> ingresarFormacionProf(@PathVariable String formacionProfP){
        try {
            formProfesionalImpl.ingresarFormacionProf(formacionProfP);
            return ResponseEntity.ok("Ingresado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/eliminar/{idFormacionProfP}")
    public ResponseEntity<String> eliminarFormacionProfPorId(@PathVariable int idFormacionProfP){
        try {
            formProfesionalImpl.eliminarFormacionProfPorId(idFormacionProfP);
            return ResponseEntity.ok("eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
