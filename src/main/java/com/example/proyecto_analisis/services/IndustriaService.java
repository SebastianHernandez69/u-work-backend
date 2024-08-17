package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Industria;
import com.example.proyecto_analisis.repository.IndustriaRepository;

@Service
public class IndustriaService {
    
    @Autowired
    private IndustriaRepository industriaRepositorio;

    public List<Industria> mostrarIndustrias(){
        return industriaRepositorio.findAll();
    }

    public void eliminarIndustriaPorId(int idIndustriaP){
        industriaRepositorio.deleteById(idIndustriaP);
    }

    public void ingresarIndustria(String industriaP){
        industriaRepositorio.ingresarIndustria(industriaP);
    }
}
