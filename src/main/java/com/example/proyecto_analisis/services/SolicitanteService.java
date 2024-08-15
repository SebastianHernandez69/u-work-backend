package com.example.proyecto_analisis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Solicitante;
import com.example.proyecto_analisis.repository.SolicitanteRepository;

@Service
public class SolicitanteService {
    
    @Autowired
    private SolicitanteRepository solicitanteRepositorio;

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

    public int ObtenerDatosVistaHome(String correo, String contrasena){

        Solicitante solicitante = solicitanteRepositorio.autenticarSolicitante(correo, contrasena);

        if(solicitante == null){
            return 0;
        } else {
            return solicitante.getIdPersona();
        }

    }

}
