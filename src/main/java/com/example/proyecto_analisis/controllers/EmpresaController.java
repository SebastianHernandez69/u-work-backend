package com.example.proyecto_analisis.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Empresa;
import com.example.proyecto_analisis.models.Persona;
import com.example.proyecto_analisis.models.dto.EmpresaDirectorDTO;
import com.example.proyecto_analisis.models.dto.OfertaEmpresaHomeDTO;
import com.example.proyecto_analisis.models.dto.VistaPerfilEmpresa_DTO;
import com.example.proyecto_analisis.services.EmpresaService;
import com.example.proyecto_analisis.services.NotificacionEmpresaService;
import com.example.proyecto_analisis.services.OfertaService;
import com.example.proyecto_analisis.services.PersonaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
    
    @Autowired
    private PersonaService personaImpl;

    @Autowired
    private EmpresaService empresaImpl;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private NotificacionEmpresaService notificacionEmpresaImpl;


    @PostMapping("/ingresar")
    public ResponseEntity<EmpresaDirectorDTO> ingresarEmpresaDirector(@RequestBody EmpresaDirectorDTO empresaDirectorDTO){


        Persona nvoPersona = new Persona();
        nvoPersona.setPrimerNombre(empresaDirectorDTO.getPrimerNombre());
        nvoPersona.setSegundoNombre(empresaDirectorDTO.getSegundoNombre());
        nvoPersona.setPrimerApellido(empresaDirectorDTO.getPrimerApellido());
        nvoPersona.setSegundoApellido(empresaDirectorDTO.getSegundoApellido());
        nvoPersona.setTelefono(empresaDirectorDTO.getTelefonoPersona());
        nvoPersona.setIdentificacion(empresaDirectorDTO.getIdentificacion());
        nvoPersona.setIdGenero(Integer.parseInt(empresaDirectorDTO.getIdGenero()));

        int idNvoPersona = personaImpl.ingresarPersona(nvoPersona);

        Empresa nvoEmpresa = new Empresa();
        
        nvoEmpresa.setNombreEmpresa(empresaDirectorDTO.getNombreEmpresa());
        nvoEmpresa.setCorreo(empresaDirectorDTO.getCorreoEmpresa());
        nvoEmpresa.setContrasena(empresaDirectorDTO.getContrasena());
        nvoEmpresa.setTelefono(empresaDirectorDTO.getTelefonoEmpresa());
        nvoEmpresa.setSitioWeb(empresaDirectorDTO.getSitioWeb());
        nvoEmpresa.setIdDireccion(Integer.parseInt(empresaDirectorDTO.getIdDireccionPais()));
        nvoEmpresa.setIdIndustria(Integer.parseInt(empresaDirectorDTO.getIdIndustria()));
        nvoEmpresa.setIdDirector(idNvoPersona);

        empresaImpl.ingresarEmpresa(nvoEmpresa);

        return ResponseEntity.ok(empresaDirectorDTO);
    }

    @GetMapping("/perfil/{idEmpresa}")
    public ResponseEntity<VistaPerfilEmpresa_DTO> obtenerVistaPerfilEmpresa(@PathVariable int idEmpresa){
        VistaPerfilEmpresa_DTO vista = empresaImpl.obtenerVistaPerfilEmpresa(idEmpresa);

        return ResponseEntity.ok(vista);
    }

    @GetMapping("/home/{idEmpresa}")
    public ResponseEntity<OfertaEmpresaHomeDTO> homeEmpresa(@PathVariable int idEmpresa){
        try {
            OfertaEmpresaHomeDTO home = ofertaService.obtenerHomeEmpresa(idEmpresa);
            return ResponseEntity.ok(home);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //obtener detalle notificacion empresa
    @GetMapping("/notificacion/{idNotificacion}")
    public ResponseEntity<Object> obtenerDetalleNotificacionEmpresa(@PathVariable int idNotificacion){
        try {
            Map<String,Object> notificacion = notificacionEmpresaImpl.obtenerNotificacionEmpresa(idNotificacion);
            
            return ResponseEntity.ok(notificacion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener notificacion: "+ e.getMessage());
        }
    }


    @PutMapping("/act-estado-notificaicon/{idNotificacion}")
    public ResponseEntity<String> cambiarEstadoNotiEmpresa(@PathVariable int idNotificacion){
        try {
            notificacionEmpresaImpl.cambiarEstadoNotiEmpresa(idNotificacion);

            return ResponseEntity.ok("Se ha cambiado el estado correctamente");
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar: " + e.getMessage());

        }
    } 

    //Eliminar oferta de empresa
    @PutMapping("/oferta/eliminar/{idOferta}")
    public ResponseEntity<String> eliminarOfertaPorId(@PathVariable int idOferta){
        try {
            ofertaService.eliminarOfertaPorId(idOferta);
            return ResponseEntity.ok("Oferta eliminada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar oferta: "+e.getMessage());
        }
    }
    
}
