package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Idioma;
import com.example.proyecto_analisis.repository.IdiomaRepository;

@Service
public class IdiomaService {
    
    @Autowired
    private IdiomaRepository idiomaRepositorio;

    public List<Idioma> mostrarIdiomas(){
        return (List<Idioma>) idiomaRepositorio.findAll();
    }

    public void eliminaridiomaPorId(int idIdiomaP){
        idiomaRepositorio.deleteById(idIdiomaP);
    }

    public void ingresarIdioma(String idioma){
        idiomaRepositorio.ingresarIdioma(idioma);
    }
}
