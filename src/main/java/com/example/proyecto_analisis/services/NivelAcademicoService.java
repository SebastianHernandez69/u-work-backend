package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.NivelAcademico;
import com.example.proyecto_analisis.repository.NivelAcademicoRepository;

@Service
public class NivelAcademicoService {
    
    @Autowired
    private NivelAcademicoRepository nivelAcademicoRepositorio;

    public List<NivelAcademico> mostrarNivelesAcademicos(){
        return (List<NivelAcademico>) nivelAcademicoRepositorio.findAll();
    }

    public void eliminarNivelAcademicoPorId(int idNivelAcad){
        nivelAcademicoRepositorio.deleteById(idNivelAcad);
    }

    public void ingresarNivelAcademico(String nivelAcademico){
        nivelAcademicoRepositorio.ingresarNivelAcademico(nivelAcademico);
    }
}
