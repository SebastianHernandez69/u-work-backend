package com.example.proyecto_analisis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Empresa;
import com.example.proyecto_analisis.models.Persona;
import com.example.proyecto_analisis.models.dto.EmpresaDirectorDTO;
import com.example.proyecto_analisis.models.dto.VistaInicioSolicitante_DTO;
import com.example.proyecto_analisis.models.dto.VistaPerfilEmpresa_DTO;
import com.example.proyecto_analisis.services.EmpresaService;
import com.example.proyecto_analisis.services.PersonaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
    
    @Autowired
    private PersonaService personaImpl;

    @Autowired
    private EmpresaService empresaImpl;


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
    
}
