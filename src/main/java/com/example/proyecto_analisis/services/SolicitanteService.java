package com.example.proyecto_analisis.services;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Obtener solicitudes del solicitante
    public List<Map<String,Object>> obtenerAplicacionesSolicitante(int idSolicitanteP){

        List<Object[]> objSolicitudes = solicitanteRepositorio.obtenerAplicacionesSolicitante(idSolicitanteP);

        if (objSolicitudes.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<Map<String,Object>> solicitudes = objSolicitudes.stream()
                .map(obj -> {
                    Map<String,Object> map = new LinkedHashMap<>();
                    map.put("idOferta", obj[0]);
                    map.put("fechaPublicacionOferta", obj[1]);
                    map.put("tituloOferta", obj[2]);
                    map.put("nombreEmpresa", obj[3]);
                    map.put("fechaSolicitud", obj[4]);
                    map.put("estadoSolicitud", obj[5]);
                    map.put("url_logo", obj[6]);

                    return map;
                }).collect(Collectors.toList());

            return solicitudes;
        }
    }
}
