package com.example.proyecto_analisis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_analisis.models.Persona;
import com.example.proyecto_analisis.models.dto.DatosPA_DTO;
import com.example.proyecto_analisis.models.dto.ExperienciaPA_DTO;
import com.example.proyecto_analisis.models.dto.FamiliaresPA_DTO;
import com.example.proyecto_analisis.models.dto.FormacionPA_DTO;
import com.example.proyecto_analisis.models.dto.HistorialMedicoPA_DTO;
import com.example.proyecto_analisis.models.dto.IdiomasPA_DTO;
import com.example.proyecto_analisis.models.dto.PerfilAplicanteDTO;
import com.example.proyecto_analisis.models.dto.SegurosPA_DTO;
import com.example.proyecto_analisis.models.dto.ValidacionPersona;
import com.example.proyecto_analisis.models.interfaces.PersonaInterface;
import com.example.proyecto_analisis.services.PersonaService;


@RestController
@RequestMapping("/api")
public class PersonaController {
    
    @Autowired
    private PersonaService personaImpl;

    @PostMapping("/persona/ingresar")
    public int ingresarPersona(@RequestBody Persona persona){
        return personaImpl.ingresarPersona(persona);
    }

    @GetMapping("/validacion/{identificacion}")
    public ValidacionPersona validacionPersona(@PathVariable String identificacion){
        PersonaInterface persona = personaImpl.obtenerUsuarioPorIdentidad(identificacion);
        
        if(persona != null){
            ValidacionPersona validacion = new ValidacionPersona();

            int idPersona = persona.getIdPersona();

            List<String> familiares = personaImpl.obtenerFamiliares(idPersona);

            validacion.setValidacion(true);
            validacion.setNombreCompleto(persona.getNombreCompleto());
            validacion.setFamiliares(familiares);

            return validacion;
        } else {
            ValidacionPersona validacion = new ValidacionPersona();

            validacion.setValidacion(false);
            validacion.setNombreCompleto(" ");
            
            return validacion;
        }
    }

    @GetMapping("/perfil/{idPersona}")
    public ResponseEntity<Object> mostrarDatosPerfilAplicante(@PathVariable int idPersona) {
        
        DatosPA_DTO datosPersonales = personaImpl.obtenerDatosPerfilApp(idPersona);
        List<FormacionPA_DTO> formacion = personaImpl.obtenerFomacionPA(idPersona);
        List<HistorialMedicoPA_DTO> historialMedico = personaImpl.obtenerHistorialMedicoPA(idPersona);
        List<SegurosPA_DTO> seguros = personaImpl.obtenerSegurosPA(idPersona);
        List<IdiomasPA_DTO> idiomas = personaImpl.obtenerIdiomasPA(idPersona);
        List<ExperienciaPA_DTO> experienciaLaboral = personaImpl.obtenerExperienciaLaboralPA(idPersona);
        List<FamiliaresPA_DTO> familiares = personaImpl.obtenerFamiliaresPA(idPersona);
        
        PerfilAplicanteDTO perfil = new PerfilAplicanteDTO();

        perfil.setDatosPersonales(datosPersonales);
        perfil.setFormacion(formacion);
        perfil.setHistorialMedico(historialMedico);
        perfil.setSeguros(seguros);
        perfil.setIdiomas(idiomas);
        perfil.setExperiencia(experienciaLaboral);
        perfil.setFamiliares(familiares);

        if(historialMedico != null){
            return ResponseEntity.ok(perfil);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener perfil");
        }

    }

}
