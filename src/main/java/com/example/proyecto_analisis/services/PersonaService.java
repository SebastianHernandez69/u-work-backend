package com.example.proyecto_analisis.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Persona;
import com.example.proyecto_analisis.models.dto.DatosPA_DTO;
import com.example.proyecto_analisis.models.dto.ExperienciaPA_DTO;
import com.example.proyecto_analisis.models.dto.FamiliaresPA_DTO;
import com.example.proyecto_analisis.models.dto.FormacionPA_DTO;
import com.example.proyecto_analisis.models.dto.HistorialMedicoPA_DTO;
import com.example.proyecto_analisis.models.dto.IdiomasPA_DTO;
import com.example.proyecto_analisis.models.dto.SegurosPA_DTO;
import com.example.proyecto_analisis.models.interfaces.PersonaInterface;
import com.example.proyecto_analisis.repository.PersonaRepository;

@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepositorio;

    // INGRESAR UNA NUEVA PERSONA
    public int ingresarPersona(Persona persona){
        Persona nvoPersona = personaRepositorio.save(persona);
        return nvoPersona.getId_persona();
    }

    // OBTENER UN USUARIO POR SU DNI
    public PersonaInterface obtenerUsuarioPorIdentidad(String identificacion){
        return personaRepositorio.obtenerUsuarioPorIdentidad(identificacion);
    }

    // OBTENER LISTA DE FAMILIARES
    public List<String> obtenerFamiliares(int idSolicitante){
        return (List<String>) personaRepositorio.obtenerFamiliares(idSolicitante);
    }

    // OBTENER DATOS PERSONALES DEL PERFIL APLICANTE
    public DatosPA_DTO obtenerDatosPerfilApp(int idPersona){

        List<Object[]> res = personaRepositorio.obtenerDatosPerfilApp(idPersona);

        if(res.isEmpty()){

            return null;

        }else{

            Object[] datos = res.get(0);

            DatosPA_DTO datosPA_DTO = new DatosPA_DTO();

            datosPA_DTO.setNombre(datos[0] != null ? datos[0].toString() : null);
            datosPA_DTO.setTitular(datos[1] != null ? datos[1].toString() : null);
            datosPA_DTO.setLugarResidencia(datos[2] != null ? datos[2].toString() : null);
            datosPA_DTO.setTelefono(datos[3] != null ? datos[3].toString() : null);
            datosPA_DTO.setCorreo(datos[4] != null ? datos[4].toString() : null);
            datosPA_DTO.setFechaNacimiento(datos[5] != null ? datos[5].toString() : null);
            datosPA_DTO.setDescripcion(datos[6] != null ? datos[6].toString() : null);
            
            return datosPA_DTO;
        }

    }

    //OBTENER FORMACION DEL APLICANTE
    public List<FormacionPA_DTO> obtenerFomacionPA(int idPersona){

        List<Object[]> res = personaRepositorio.obtenerFormacionPA(idPersona);

        if(res.isEmpty()){
            return Collections.emptyList();
        }else{

            return res.stream()
                .map(obj -> new FormacionPA_DTO(
                        (String) obj[0],
                        (String) obj[1],
                        (String) obj[2],
                        (String) obj[3]
                ))
                .collect(Collectors.toList());
        }
    }

    //OBTENER HISTORIAL MEDICO DEL APLICANTE
    public List<HistorialMedicoPA_DTO> obtenerHistorialMedicoPA(int idPersona){

        List<Object[]> res = personaRepositorio.obtenerHistorialMedicoPA(idPersona);

        if(res.isEmpty()){
            return Collections.emptyList();
        }else {
            return res.stream()
                    .map(obj -> new HistorialMedicoPA_DTO(
                        (String) obj[0],
                        (String) obj[1]
                    ))
                    .collect(Collectors.toList());
        }
    }

    //OBTENER SEGUROS DEL APLICANTE
    public List<SegurosPA_DTO> obtenerSegurosPA(int idPersona){

        List<Object[]> res = personaRepositorio.obtenerSegurosPA(idPersona);

        if(res.isEmpty()){
            return Collections.emptyList();
        } else {

            return res.stream()
                    .map(obj -> new SegurosPA_DTO(
                        (String) obj[0],
                        (String) obj[1],
                        (String) obj[2],
                        (String) obj[3]
                    ))
                    .collect(Collectors.toList());
        }
    }

    //
    public List<IdiomasPA_DTO> obtenerIdiomasPA(int idPersona){

        List<Object[]> res = personaRepositorio.obtenerIdiomasPA(idPersona);

        if(res.isEmpty()){
            return Collections.emptyList();
        } else {

            return res.stream()
                    .map(obj -> new IdiomasPA_DTO(
                        (String) obj[0],
                        (String) obj[1]
                    ))
                    .collect(Collectors.toList());
        }
    }

    //
    public List<ExperienciaPA_DTO> obtenerExperienciaLaboralPA(int idPersona){

        List<Object[]> res = personaRepositorio.obtenerExperienciaLaboralPA(idPersona);

        if(res.isEmpty()){
            return Collections.emptyList();
        } else {

            return res.stream()
                    .map(obj -> new ExperienciaPA_DTO(
                        (String) obj[0],
                        (String) obj[1],
                        (String) obj[2],
                        (String) obj[3]
                    )).collect(Collectors.toList());
        }
    }

    //
    public List<FamiliaresPA_DTO> obtenerFamiliaresPA(int idPersona){

        List<Object[]> res = personaRepositorio.obtenerFamiliaresPA(idPersona);

        if(res.isEmpty()){
            return Collections.emptyList();
        }

        return res.stream()
                .map(obj -> new FamiliaresPA_DTO(
                    (String) obj[0],
                    (String) obj[1],
                    (String) obj[2],
                    (String) obj[3]
                )).collect(Collectors.toList());

    }

    public Persona actualizarPersonaPorId(int idPersona, Persona actPersona){

        Optional<Persona> optPersona = personaRepositorio.findById(idPersona);

        if(optPersona.isPresent()){
            Persona extPersona = optPersona.get();

            extPersona.setPrimerNombre(actPersona.getPrimerNombre());
            extPersona.setSegundoApellido(actPersona.getSegundoNombre());
            extPersona.setPrimerApellido(actPersona.getPrimerApellido());
            extPersona.setSegundoApellido(actPersona.getSegundoApellido());
            extPersona.setTelefono(actPersona.getTelefono());
            extPersona.setIdentificacion(actPersona.getIdentificacion());
            extPersona.setIdGenero(actPersona.getIdGenero());

            return personaRepositorio.save(extPersona);
        }else {
            return null;
        }

    }

    // Autentica admin
    public int autenticarAdmin(String correoP, String contrasenaP){

        int idAdmin = personaRepositorio.autenticarAdmin(correoP, contrasenaP);

        return idAdmin;

    }
}
