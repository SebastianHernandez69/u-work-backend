package com.example.proyecto_analisis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.dto.LoginDTO;
import com.example.proyecto_analisis.services.EmpresaService;
import com.example.proyecto_analisis.services.SolicitanteService;

@RestController
@RequestMapping("/api")
public class LoginDTOController {
    
    @Autowired
    private SolicitanteService solicitanteImpl;

    @Autowired
    private EmpresaService empresaImpl;

    @PostMapping("/login/solicitante")
    public ResponseEntity<Integer> autenticarSolicitante(@RequestBody LoginDTO loginDTO){
        int idPersonaSoli = solicitanteImpl.autenticarSolicitante(loginDTO.getCorreo(), loginDTO.getContrasena());
        return ResponseEntity.ok(idPersonaSoli);
    }

    @GetMapping("/login/empresa")
    public ResponseEntity<Integer> autenticarDirectorEmpresa(@RequestBody LoginDTO loginDTO){
        int idDirectorEmpresa = empresaImpl.autenticarDirectorEmpresa(loginDTO.getCorreo(), loginDTO.getContrasena());
        return ResponseEntity.ok(idDirectorEmpresa);
    }

}
