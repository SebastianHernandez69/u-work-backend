package com.example.proyecto_analisis.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.ExperienciaLaboral;
import com.example.proyecto_analisis.models.Solicitante;
import com.example.proyecto_analisis.repository.ExperienciaLaboralRepository;
import com.example.proyecto_analisis.repository.SolicitanteRepository;

@Service
public class SolicitanteService {
    
    @Autowired
    private SolicitanteRepository solicitanteRepositorio;

    @Autowired
    private ExperienciaLaboralRepository expLabRepositorio;

    public void ingresarSolicitante(Solicitante solicitante){
        solicitanteRepositorio.save(solicitante);
        
    }

    public int autenticarSolicitante(String correo, String contrasena){

        Solicitante solicitante = solicitanteRepositorio.autenticarSolicitante(correo, contrasena);

        if(solicitante == null){
            return 0;
        } else {
            return solicitante.getIdPersona();
        }

    }

    public Solicitante actualizarSolicitante(int idPersona, Solicitante actSolicitante){

        Optional<Solicitante> optSolicitante = solicitanteRepositorio.findById(idPersona);

        if(optSolicitante.isPresent()){

            Solicitante extSolicitante = optSolicitante.get();

            extSolicitante.setCorreo(actSolicitante.getCorreo());
            extSolicitante.setContrasena(actSolicitante.getContrasena());
            extSolicitante.setFechaNacimiento(actSolicitante.getFechaNacimiento());
            extSolicitante.setTitular(actSolicitante.getTitular());
            extSolicitante.setDescripcion(actSolicitante.getDescripcion());
            extSolicitante.setEstadoCivil(actSolicitante.getEstadoCivil());
            extSolicitante.setLugarNacimiento(actSolicitante.getLugarNacimiento());
            extSolicitante.setLugarResidencia(actSolicitante.getLugarResidencia());

            return solicitanteRepositorio.save(extSolicitante);
        }else {
            return null;
        }
    }

    public void aggExperienciaLaboral(ExperienciaLaboral expLaboral){
        expLabRepositorio.save(expLaboral); 
    }

    // Agg experiencia laboral
    public void insertarHistorialAcademico(
        int idPersonaP, 
        int idNivelAcademicoP, 
        int idFormacionAcP, 
        String tituloP, 
        Date fechaEgresoP, 
        String INSTITUCIONP
    ){
        solicitanteRepositorio.insertarHistorialAcademico(idPersonaP, idNivelAcademicoP, idFormacionAcP, tituloP, fechaEgresoP, INSTITUCIONP);
    }

    //Agg idiomas del solicitante
    public void ingresarSolicitanteIdioma(
        int idPersonaP,
        int idIdiomaP,
        int idNivelP
    ){
        solicitanteRepositorio.ingresarSolicitanteIdioma(idPersonaP, idIdiomaP, idNivelP);
    }

    //Agg informacion de seguro del solicitante
    public void insertarSeguroSolicitante(
        int idPersonaP,
        int idTipoSeguroP,
        Date fechaAfiliacionP,
        Date fechaExpiracionP,
        String numeroAfiliacionP
    ){
        solicitanteRepositorio.insertarSeguroSolicitante(idPersonaP,idTipoSeguroP,fechaAfiliacionP,fechaExpiracionP,numeroAfiliacionP);
    }

    // Agg familiar de solicitante
    public void ingresarFamiliarSolicitante(
        int idFamiliar, 
        int idParentesco, 
        int idSolicitante
    ){
        solicitanteRepositorio.ingresarFamiliarSolicitante(idFamiliar, idParentesco, idSolicitante);
    }

    // Agg historial medico solicitante
    public void ingresarHistorialMedico(
        String descripcion, 
        int idCondicion, 
        int idPersona
    ){
        solicitanteRepositorio.ingresarHistorialMedico(descripcion, idCondicion, idPersona);
    }
}
