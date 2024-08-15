package com.example.proyecto_analisis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.services.SolicitudesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudesController {

    @Autowired
    SolicitudesService solicitudesService;
    
    @PutMapping("/actualizar")
    public ResponseEntity<String> putMethodName(@RequestParam("idSolicitud") int idSolicitud, @RequestParam("idEstadoSolicitud") int idEstadoSolicitud) {
        try {
            solicitudesService.cambiarEstadoSolicitud(idSolicitud, idEstadoSolicitud);
            return ResponseEntity.ok("Se ha cambiado el estado de la solicitud correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar: " + e.getMessage());
        }
    }

}
