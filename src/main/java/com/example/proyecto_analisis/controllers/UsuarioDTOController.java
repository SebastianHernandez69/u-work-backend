package com.example.proyecto_analisis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Persona;
import com.example.proyecto_analisis.models.Solicitante;
import com.example.proyecto_analisis.models.dto.UsuarioDTO;
import com.example.proyecto_analisis.services.PersonaService;
import com.example.proyecto_analisis.services.SolicitanteService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class UsuarioDTOController {
    
    @Autowired
    private PersonaService personaImpl;

    @Autowired
    private SolicitanteService solicitanteimpl;


    @PostMapping("/usuario/ingresar")
    public ResponseEntity<UsuarioDTO> ingresarUsuario(@RequestBody UsuarioDTO usuarioDTO){
            
        Persona nvoPersona = new Persona();

        nvoPersona.setPrimerNombre(usuarioDTO.getPrimerNombre());
        nvoPersona.setSegundoNombre(usuarioDTO.getSegundoNombre());
        nvoPersona.setPrimerApellido(usuarioDTO.getPrimerApellido());
        nvoPersona.setSegundoApellido(usuarioDTO.getSegundoApellido());
        nvoPersona.setTelefono(usuarioDTO.getTelefono());
        nvoPersona.setIdentificacion(usuarioDTO.getIdentificacion());
        nvoPersona.setIdGenero(Integer.parseInt(usuarioDTO.getIdGenero()));

        int idNvoPersona = personaImpl.ingresarPersona(nvoPersona);

        Solicitante nvoSolicitante = new Solicitante();

        nvoSolicitante.setIdPersona(idNvoPersona);
        nvoSolicitante.setCorreo(usuarioDTO.getCorreo());
        nvoSolicitante.setContrasena(usuarioDTO.getContrasena());
        nvoSolicitante.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        nvoSolicitante.setTitular(usuarioDTO.getTitular());
        nvoSolicitante.setDescripcion(usuarioDTO.getDescripcion());
        nvoSolicitante.setLugarResidencia(Integer.parseInt(usuarioDTO.getIdLugarResidencia()));
        nvoSolicitante.setLugarNacimiento(Integer.parseInt(usuarioDTO.getIdLugarNacimiento()));
        nvoSolicitante.setEstadoCivil(Integer.parseInt(usuarioDTO.getIdGenero()));

        solicitanteimpl.ingresarSolicitante(nvoSolicitante);

        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/actualizarUsuario/{idPersona}")
    public ResponseEntity<Object> actualizarUsuario(@PathVariable int idPersona, @RequestBody UsuarioDTO usuarioDtoActualizado){

        //Mapear persona
        Persona persona = new Persona();

        persona.setPrimerNombre(usuarioDtoActualizado.getPrimerNombre());
        persona.setSegundoNombre(usuarioDtoActualizado.getSegundoNombre());
        persona.setPrimerApellido(usuarioDtoActualizado.getPrimerApellido());
        persona.setSegundoApellido(usuarioDtoActualizado.getSegundoApellido());
        persona.setTelefono(usuarioDtoActualizado.getTelefono());
        persona.setIdentificacion(usuarioDtoActualizado.getIdentificacion());
        persona.setIdGenero(Integer.parseInt(usuarioDtoActualizado.getIdGenero()));

        //Mapear solicitante
        Solicitante solicitante = new Solicitante();

        solicitante.setCorreo(usuarioDtoActualizado.getCorreo());
        solicitante.setContrasena(usuarioDtoActualizado.getContrasena());
        solicitante.setFechaNacimiento(usuarioDtoActualizado.getFechaNacimiento());
        solicitante.setTitular(usuarioDtoActualizado.getTitular());
        solicitante.setDescripcion(usuarioDtoActualizado.getDescripcion());
        solicitante.setLugarResidencia(Integer.parseInt(usuarioDtoActualizado.getIdLugarResidencia()));
        solicitante.setLugarNacimiento(Integer.parseInt(usuarioDtoActualizado.getIdLugarNacimiento()));
        solicitante.setEstadoCivil(Integer.parseInt(usuarioDtoActualizado.getIdGenero()));

        //Actualizar
        Persona actPersona = personaImpl.actualizarPersonaPorId(idPersona, persona);
        Solicitante actSolicitante = solicitanteimpl.actualizarSolicitante(idPersona, solicitante);

        if(actPersona != null && actSolicitante != null){
            return ResponseEntity.ok(usuarioDtoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar");
        }

    }

}
