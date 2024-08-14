package com.example.proyecto_analisis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Solicitante;
import com.example.proyecto_analisis.models.dto.PreferenciasUsuarioDTO;
import com.example.proyecto_analisis.services.PreferenciasService;
import com.example.proyecto_analisis.services.SolicitanteService;

@RestController
@RequestMapping("/api")
public class SolicitanteController {
    
    @Autowired
    private SolicitanteService solicitanteImpl;

    @Autowired
    private PreferenciasService preferenciasImpl;

    @PostMapping("/solicitante/ingresar")
    public ResponseEntity<String> ingresarSolicitante(@RequestBody Solicitante solicitante){
        
        try {
            solicitanteImpl.ingresarSolicitante(solicitante);
            return ResponseEntity.ok("Solicitud exitosa");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al ingresar solicitante");
        }
    }

    @GetMapping("/solicitante/preferencias/{idPersona}")
    public ResponseEntity<PreferenciasUsuarioDTO> obtenerPreferenciasUsuarios(@PathVariable int idPersona){

        PreferenciasUsuarioDTO preferenciasUsuarioDTO = preferenciasImpl.obtenerPerferencias(idPersona);

        return ResponseEntity.ok(preferenciasUsuarioDTO);
        
    }

    @GetMapping("/solicitante/preferencias/act/{idPersona}")
    public ResponseEntity<String> actualizarPreferencias(@PathVariable int idPersona){

        try {
            preferenciasImpl.eliminarPreferenciasUsuario(idPersona);
            return ResponseEntity.ok("Actualizacion correcta");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }

    }
}
