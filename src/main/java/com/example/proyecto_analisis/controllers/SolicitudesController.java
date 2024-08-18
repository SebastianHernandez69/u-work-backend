package com.example.proyecto_analisis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.dto.SolicitudDTO;
import com.example.proyecto_analisis.services.SolicitudesService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudesController {

    @Autowired
    SolicitudesService solicitudesService;
    
    @PutMapping("/actualizar")
    public ResponseEntity<String> cambiarEstadoSolicitud(@RequestParam("idSolicitud") int idSolicitud, @RequestParam("idEstadoSolicitud") int idEstadoSolicitud) {
        try {
            solicitudesService.cambiarEstadoSolicitud(idSolicitud, idEstadoSolicitud);
            return ResponseEntity.ok("Se ha cambiado el estado de la solicitud correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar: " + e.getMessage());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudDTO solicitud) {
        try {
            solicitudesService.crearSolicitud(solicitud);
            return ResponseEntity.ok("Se ha cambiado el estado de la solicitud correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar: " + e.getMessage());
        }
    }

    @PutMapping("/cambiar-estado/{idSolicitanteP}")
    public ResponseEntity<String> cambiarEstadoSolicitudSolicitante(@PathVariable int idSolicitanteP,@RequestBody Map<String,Integer> datosEstado){

        try {
            int idSolicitud = datosEstado.get("idSolicitud");
            int idEstado = datosEstado.get("idEstado");
            
            solicitudesService.aceptarRechazarSolicitudSolicitante(idEstado,idSolicitud,idSolicitanteP);

            return ResponseEntity.ok("Cambio de estado de solicitud correcto");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al cambiar estado"+e.getMessage());
        }
    }

}
