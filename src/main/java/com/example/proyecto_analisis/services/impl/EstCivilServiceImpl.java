package com.example.proyecto_analisis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.EstadoCivil;
import com.example.proyecto_analisis.repository.EstadoCivilRespository;
import com.example.proyecto_analisis.services.EstCivilService;

@Service
public class EstCivilServiceImpl implements EstCivilService{

    @Autowired
    private EstadoCivilRespository estCivilRepositorio;

    @Override
    public List<EstadoCivil> mostrarEstadosCiviles() {
        return(List<EstadoCivil>) estCivilRepositorio.findAll();
    }

    @Override
    public void eliminarEstadoCivilPorId(int idEstadoCivilP){
        estCivilRepositorio.deleteById(idEstadoCivilP);
    }

    @Override
    public void ingresarEstadoCivil(String estadoCivil){
        estCivilRepositorio.ingresarEstadoCivil(estadoCivil);
    }
    
}
