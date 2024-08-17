package com.example.proyecto_analisis.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.CondicionMedica;
import com.example.proyecto_analisis.repository.CondicionMedicaRepository;

@Service
public class CondicionMedicaService {
    @Autowired
    private CondicionMedicaRepository condicionMedicaRepositorio;

    public List<CondicionMedica> mostrarCondicionMed(){
        return (List<CondicionMedica>) condicionMedicaRepositorio.findAll();
    }

    public void eliminarCondicionMedPorId(int idCondicionMed){
        condicionMedicaRepositorio.deleteById(idCondicionMed);
    }

    public void ingresarCondicionMedica(String condicionMed){
        condicionMedicaRepositorio.ingresarCondicionMedica(condicionMed);
    }

    public String obtenerTablas(){
        return condicionMedicaRepositorio.obtenerTablas();
    }

    public List<String> nomTablas(){
        return (List<String>) condicionMedicaRepositorio.nomTablas();
    }
}
