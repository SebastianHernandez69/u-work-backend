package com.example.proyecto_analisis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Solicitante;
import com.example.proyecto_analisis.models.dto.ActPreferenciasDTO;
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

    @PostMapping("/solicitante/preferencias/act/{idPersona}")
    public ResponseEntity<String> actualizarPreferencias(@PathVariable int idPersona, @RequestBody ActPreferenciasDTO actPreferencias){

        try {
            //Limpiar tablas de preferencias
            preferenciasImpl.eliminarPreferenciasUsuario(idPersona);

            //Actualizar datos
            List<Integer> puestos = actPreferencias.getPuestos();
            List<Integer> modalidades = actPreferencias.getModalidades();
            List<Integer> contratos = actPreferencias.getContratos();

            for (Integer puesto : puestos) {
                preferenciasImpl.ingresarPreferenciaPuesto(puesto, idPersona);
            }

            for (Integer modalidad : modalidades) {
                preferenciasImpl.ingresarPreferenciaModalidad(modalidad, idPersona);
            }

            for (Integer contrato : contratos) {
                preferenciasImpl.ingresarPreferenciaContrato(idPersona, contrato);
            }

            return ResponseEntity.ok("Preferencias actualizadas correntamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.toString());
        }


    }
}
