package com.example.proyecto_analisis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_analisis.models.Modalidad;
import com.example.proyecto_analisis.repository.ModalidadRepository;

@Service
public class ModalidadService {
    
    @Autowired
    private ModalidadRepository modalidadRepositorio;

    public List<Modalidad> mostrarModalidades(){
        return (List<Modalidad>) modalidadRepositorio.findAll();
    }

    public void eliminarModalidadPorId(int idModalidad){
        modalidadRepositorio.deleteById(idModalidad);
    }

    public void ingresarModalidad(String modalidad){
        modalidadRepositorio.ingresarModalidad(modalidad);
    }
}
