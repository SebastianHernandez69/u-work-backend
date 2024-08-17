package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.NivelIdioma;
import com.example.proyecto_analisis.repository.NivelIdiomaRepository;

@Service
public class NivelIdiomaService {
    @Autowired
    private NivelIdiomaRepository nivelIdiomaRepositorio;

    public List<NivelIdioma> mostrarNivelIdioma(){
        return (List<NivelIdioma>) nivelIdiomaRepositorio.findAll();
    }

    public void eliminarNivelIdioma(int idNivelIdioma){
        nivelIdiomaRepositorio.deleteById(idNivelIdioma);
    }

    public void ingresarNivelIdioma(String nivelIdioma){
        nivelIdiomaRepositorio.ingresarNivelIdioma(nivelIdioma);
    }
}
